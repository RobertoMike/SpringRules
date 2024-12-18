package io.github.robertomike.jakidate.constraints.objects.comparation

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.utils.getFieldsByAnnotation
import io.github.robertomike.jakidate.utils.getValue
import io.github.robertomike.jakidate.utils.validate
import java.lang.reflect.Field

abstract class CompareFieldsConstraint<A : Annotation> : SimpleMessageConstraint<A, Any>() {
    protected abstract val annotationField: Class<out Annotation>
    protected abstract val message: String
    protected open val invertedLogic
        get() = false
    private val nameAnnotation
        get() = annotationField.simpleName

    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
        val fields = getFields(value)

        return fields.validate { validateGroup(it, util, value) }
    }

    abstract fun groupBy(fields: Sequence<Field>): Map<String, List<Field>>

    private fun getFields(value: Any): Map<String, List<Field>> {
        val fields = groupBy(value.getFieldsByAnnotation(annotationField))

        if (fields.isEmpty()) {
            throw RulesException("You need to use the $nameAnnotation annotation on more than one field")
        }

        return fields
    }

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