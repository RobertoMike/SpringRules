package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.Different
import io.github.robertomike.jakidate.validations.DifferentField
import java.lang.reflect.Field

class DifferentConstraint : SimpleMessageConstraint<Different, Any>() {
    private val annotationField = DifferentField::class.java

    override fun isValid(value: Any, util: MessageUtil): Boolean {
        val fields = getFields(value)

        return fields.all { (_, list) -> validateGroup(list, util, value) }
    }

    private fun getFields(value: Any): Map<String, List<Field>> {
        val fields = value.javaClass.declaredFields
            .asSequence()
            .filter { it.isAnnotationPresent(annotationField) }
            .groupBy { it.getAnnotation(annotationField).key }
            .toMap()

        if (fields.isEmpty()) {
            throw RulesException("You need to use the @RequiredIfField annotation on the condition field and necessary field")
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

        return fields.map {
            getValue(it, original)
        }
            .zipWithNext()
            .any { it.first != it.second }
    }

    private fun getValue(field: Field, original: Any): Any {
        field.trySetAccessible()

        return field.get(original)
            ?: throw RulesException("The value of the field cannot be null when is condition field")
    }
}