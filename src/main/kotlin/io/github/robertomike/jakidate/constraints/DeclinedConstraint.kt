package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.declinedStringValues
import io.github.robertomike.jakidate.validations.Declined
import java.lang.UnsupportedOperationException

class DeclinedConstraint: SimpleConstraint<Declined, Any>() {
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Boolean -> value == false
            is String -> value.lowercase() in declinedStringValues
            is Number -> value.toInt() == 0
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}