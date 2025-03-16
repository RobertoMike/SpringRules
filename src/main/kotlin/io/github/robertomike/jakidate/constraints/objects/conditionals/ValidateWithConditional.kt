package io.github.robertomike.jakidate.constraints.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.Expression
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.*
import java.lang.reflect.Field

/**
 * Validates a condition constraint based on a given annotation field and message.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 * @see RequiredConstraint
 * @see ExcludeConstraint
 * @param original The original object being validated.
 * @param util The message utility used for validation.
 * @param checkEmpty Whether to check for empty values.
 * @param invertCheckLogic Whether to invert the check logic (default is false).
 */
class ValidateWithConditional(
    private val original: Any,
    private val util: MessageUtil,
    private val checkEmpty: Boolean,
    private val invertCheckLogic: Boolean = false
) {
    /**
     * Checks if the annotation field is valid based on the given message and unless condition.
     *
     * @param annotationField The annotation field to validate.
     * @param message The message to use for validation.
     * @param unless Whether the validation should be inverted (default is false).
     * @return Whether the annotation field is valid.
     */
    fun isValid(annotationField: Class<out Annotation>, message: String, unless: Boolean = false): Boolean {
        return getFields(annotationField)
            .validate { validateGroup(it, message, annotationField, !unless) }
    }

    /**
     * Gets the fields associated with the given annotation field.
     *
     * @param annotationField The annotation field to get fields for.
     * @return The fields associated with the annotation field.
     */
    private fun getFields(annotationField: Class<out Annotation>): Map<String, List<Field>> {
        return original.getFieldsByAnnotation(annotationField)
            .groupBy { it.getAnnotation(annotationField).getFieldValue("key") }
    }

    /**
     * Validates a group of fields based on the given message, annotation field, and unless condition.
     *
     * @param fields The fields to validate.
     * @param message The message to use for validation.
     * @param annotationField The annotation field to validate.
     * @param unless Whether the validation should be inverted.
     * @return Whether the field is valid.
     */
    private fun validateGroup(fields: List<Field>, message: String, annotationField: Class<out Annotation>, unless: Boolean): Boolean {
        val fieldCondition = fields.firstOrNull { it.getAnnotation(annotationField).getFieldValue("condition") }
            ?: throw RulesException("You need to use the ${annotationField.name} annotation on the condition field")

        val condition = getCondition(fieldCondition, annotationField, unless)

        val fieldsToValidate = fields.filterNot { it.getAnnotation(annotationField).getFieldValue("condition") }

        if (condition) {
            util.addParameters(Pair("conditionField", fieldCondition.name))
            return validateFields(fieldsToValidate, message)
        }

        return true
    }

    /**
     * Validates the fields based on the given message.
     *
     * @param fields The fields to validate.
     * @param message The message to use for validation.
     * @return Whether the fields are valid.
     */
    private fun validateFields(fields: List<Field>, message: String): Boolean {
        return fields.all { field ->
            val value = field.getValue(original)
            val result = value == null || checkIfEmpty(value)

            if (result != invertCheckLogic) {
                util.addMessageForProperty(field, message)
                return false
            }

            true
        }
    }

    /**
     * Gets the condition based on the given field, annotation field, and unless condition.
     *
     * @param field The field to get the condition for.
     * @param annotationField The annotation field.
     * @param unless Whether the condition should be inverted.
     * @return The condition.
     */
    private fun getCondition(field: Field, annotationField: Class<out Annotation>, unless: Boolean): Boolean {
        val annotationCondition = field.getAnnotation(annotationField)
        val value = field.getValue(original)
            ?: throw RulesException("The value of the field cannot be null when is a condition field")

        if (value is Boolean) {
            return value == unless
        }

        val constructor = annotationCondition.getFieldValue<Class<out Expression>>("expression")
            .constructors
            .firstOrNull { it.parameters.isEmpty() }
            ?: throw RulesException("The expression need to have a empty constructor")

        val expression = constructor.newInstance() as Expression

        return expression.apply(value, !unless)
    }

    /**
     * Checks if the given value is empty.
     *
     * @param value The value to check.
     * @return Whether the value is empty.
     */
    private fun checkIfEmpty(value: Any?): Boolean {
        if (value == null || !checkEmpty) {
            return false
        }

        return when (value) {
            is String -> value.isEmpty()
            is Collection<*> -> value.isEmpty()
            else -> false
        }
    }
}