package io.github.robertomike.jakidate.constraints.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.Expression
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.*
import java.lang.reflect.Field

class ValidateWithConditional(
    private val original: Any,
    private val util: MessageUtil,
    private val checkEmpty: Boolean,
    private val invertCheckLogic: Boolean = false
) {
    fun isValid(annotationField: Class<out Annotation>, message: String, unless: Boolean = false): Boolean {
        return getFields(annotationField)
            .validate { validateGroup(it, message, annotationField, !unless) }
    }

    private fun getFields(annotationField: Class<out Annotation>): Map<String, List<Field>> {
        return original.getFieldsByAnnotation(annotationField)
            .groupBy { it.getAnnotation(annotationField).getFieldValue("key") }
    }

    private fun validateGroup(fields: List<Field>, message: String, annotationField: Class<out Annotation>, unless: Boolean): Boolean {
        val fieldCondition = fields.firstOrNull { it.getAnnotation(annotationField).getFieldValue("conditional") }
            ?: throw RulesException("You need to use the ${annotationField.name} annotation on the condition field")

        val condition = getCondition(fieldCondition, annotationField, unless)

        val fieldsToValidate = fields.filterNot { it.getAnnotation(annotationField).getFieldValue("conditional") }

        if (condition) {
            util.addParameters(Pair("conditionField", fieldCondition.name))
            return validateFields(fieldsToValidate, message)
        }

        return true
    }

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

    private fun getCondition(field: Field, annotationField: Class<out Annotation>, unless: Boolean): Boolean {
        val annotationCondition = field.getAnnotation(annotationField)
        val value = field.getValue(original)
            ?: throw RulesException("The value of the field cannot be null when is a condition field")

        if (value is Boolean) {
            return value == unless
        }

        val expression = annotationCondition.getFieldValue<Class<out Expression>>("expression")
            .constructors
            .first { it.parameters.isEmpty() }
            .newInstance() as Expression

        return expression.apply(value, !unless)
    }

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