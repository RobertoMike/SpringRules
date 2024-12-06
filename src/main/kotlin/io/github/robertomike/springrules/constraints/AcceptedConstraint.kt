package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.exceptions.NotSupportedException
import io.github.robertomike.springrules.validations.AcceptedValidation

class AcceptedConstraint: SimpleConstraint<AcceptedValidation, Any> {
    private val acceptedStringValues = listOf("true", "y", "1")

    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Boolean -> value == true
            is String -> value.lowercase() in acceptedStringValues
            is Number -> value.toInt() == 1
            else -> throw NotSupportedException("Type ${value::class.simpleName} is not supported")
        }
    }
}