package io.github.robertomike.jakidate.constraints.objects.comparation

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.utils.getFieldsByAnnotation
import io.github.robertomike.jakidate.utils.getValue
import io.github.robertomike.jakidate.utils.validate
import java.lang.reflect.Field

/**
 * A constraint that compares fields of an object based on certain conditions.
 *
 * This class represents a constraint that compares fields of an object based on the provided annotation.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
abstract class CompareFields<A : Annotation> : SimpleMessageConstraint<A, Any>() {
    /**
     * The annotation field that is used to annotate the fields to be compared.
     */
    protected abstract val annotationField: Class<out Annotation>
    /**
     * The error message that is used when the comparison fails.
     */
    protected abstract val message: String
    /**
     * Whether the comparison logic should be inverted.
     *
     * If true, the comparison will return true if the fields are not equal, and false otherwise.
     *
     * @return true if the comparison logic should be inverted, false otherwise
     */
    protected open val invertedLogic
        get() = false
    /**
     * The name of the annotation that is used to annotate the fields to be compared.
     */
    private val nameAnnotation
        get() = annotationField.simpleName

    /**
     * Checks if the given value is valid based on the comparison of its fields.
     *
     * This method checks if the fields of the given value are valid based on the comparison logic defined in the annotation.
     *
     * @param value the value to be validated
     * @param util the message utility to be used for validation
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
        val fields = getFields(value)

        return fields.validate { validateGroup(it, util, value) }
    }

    /**
     * Groups the given fields by their annotation.
     *
     * @param fields the fields to be grouped
     * @return a map of fields grouped by their annotation
     */
    abstract fun groupBy(fields: Sequence<Field>): Map<String, List<Field>>

    /**
     * Gets the fields of the given value that are annotated with the annotation field.
     *
     * @param value the value to get the fields from
     * @return a map of fields grouped by their annotation
     */
    private fun getFields(value: Any): Map<String, List<Field>> {
        val fields = groupBy(value.getFieldsByAnnotation(annotationField))

        if (fields.isEmpty()) {
            throw RulesException("You need to use the $nameAnnotation annotation on more than one field")
        }

        return fields
    }

    /**
     * Validates a group of fields based on the comparison logic defined in the annotation.
     *
     * @param fields the fields to be validated
     * @param util the message utility to be used for validation
     * @param original the original value
     * @return true if the fields are valid, false otherwise
     */
    private fun validateGroup(fields: List<Field>, util: MessageUtil, original: Any): Boolean {
        if (fields.size <= 1) {
            throw RulesException("You need to use the $nameAnnotation annotation on at least two fields with the same key")
        }
        /**
         * Take the list of fields, transform them into values
         * use function zipWithNext() to compare each value with the next one
         */
        val sequence = fields.asSequence()
            .map { it.getValue(original) }
            .zipWithNext()

        val result = if (invertedLogic) sequence.any { it.first != it.second }
        else sequence.any { it.first == it.second }

        if (!result) {
            util.addMessageForProperty(fields.first(), message, Pair("otherFields", fields.joinToString(", ") { it.name }))
        }

        return result
    }
}