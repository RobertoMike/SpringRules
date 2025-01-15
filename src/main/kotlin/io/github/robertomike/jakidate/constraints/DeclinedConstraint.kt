package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.declinedStringValues
import io.github.robertomike.jakidate.validations.Declined
import java.lang.UnsupportedOperationException

/**
 * A constraint validator that checks if a value is declined.
 *
 * This validator supports the following types:
 * - Boolean: checks if the value is false
 * - String: checks if the value is in the list of declined string values
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 * @see io.github.robertomike.jakidate.utils.declinedStringValues
 */
class DeclinedConstraint: SimpleConstraint<Declined, Any>() {
    /**
     * Checks if the given value is declined.
     *
     * @param value the value to check
     * @return true if the value is declined, false otherwise
     * @throws UnsupportedOperationException if the value type is not supported
     */
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Boolean -> value == false
            is String -> value.lowercase() in declinedStringValues
            is Number -> value.toInt() == 0
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}