package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.acceptedStringValues
import io.github.robertomike.jakidate.validations.Accepted
import java.lang.UnsupportedOperationException

class AcceptedConstraint: SimpleConstraint<Accepted, Any>() {
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Boolean -> value == true
            is String -> value.lowercase() in acceptedStringValues
            is Number -> value.toInt() == 1
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}