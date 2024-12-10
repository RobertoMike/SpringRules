package io.github.robertomike.jakidate.constraints.objects

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.utils.getFieldsByAnnotation
import io.github.robertomike.jakidate.utils.getValue
import io.github.robertomike.jakidate.validations.`object`.Different
import io.github.robertomike.jakidate.validations.`object`.DifferentField
import java.lang.reflect.Field

class DifferentConstraint: SimpleMessageConstraint<Different, Any>() {
    private val annotationField = DifferentField::class.java

    override fun isValid(value: Any, util: MessageUtil): Boolean {
        val fields = getFields(value)

        return fields.all { (_, list) -> validateGroup(list, util, value) }
    }

    private fun getFields(value: Any): Map<String, List<Field>> {
        val fields = value.getFieldsByAnnotation(annotationField)
            .groupBy { it.getAnnotation(annotationField).key }

        if (fields.isEmpty()) {
            throw RulesException("You need to use the @DifferentField annotation on more than one field")
        }

        return fields
    }

    private fun validateGroup(fields: List<Field>, util: MessageUtil, original: Any): Boolean {
        if (fields.size <= 1) {
            throw RulesException("You need to use the @DifferentField annotation on at least two fields with the same key")
        }
        /**
         * Take the list of fields, transform them into values
         * use function zipWithNext() to compare each value with the next one
         */
        val result = fields.asSequence()
            .map { it.getValue(original) }
            .zipWithNext()
            .any { it.first != it.second }

        if (!result) {
            util.resetDefaultMessage()
            fields.forEach {
                util.addMessageForProperty(it, "different")
            }
        }

        return result
    }
}