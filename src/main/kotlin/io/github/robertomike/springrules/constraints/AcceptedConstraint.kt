package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.Accepted
import java.lang.UnsupportedOperationException

class AcceptedConstraint: SimpleConstraint<Accepted, Any>() {
    private val acceptedStringValues = listOf("true", "y", "1")

    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Boolean -> value == true
            is String -> value.lowercase() in acceptedStringValues
            is Number -> value.toInt() == 1
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}