package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.acceptedStringValues
import io.github.robertomike.jakidate.validations.Accepted
import java.lang.UnsupportedOperationException

/**
 * A constraint validator that checks if a given value is accepted.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class AcceptedConstraint: SimpleConstraint<Accepted, Any>() {
    /**
     * Checks if the given value is accepted.
     *
     * @param value the value to be validated
     * @return true if the value is accepted, false otherwise
     * @throws UnsupportedOperationException if the type of the value is not supported
     */
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Boolean -> value == true
            is String -> value.lowercase() in acceptedStringValues
            is Number -> value.toInt() == 1
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}