package io.github.robertomike.jakidate.constraints.objects

import io.github.robertomike.jakidate.utils.acceptedStringValues
import io.github.robertomike.jakidate.utils.declinedStringValues

/**
 * Represents an expression that can be applied to a value to determine its validity.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
open class Expression {
    /**
     * Applies the expression to the given value, taking into account the `unless` condition.
     *
     * @param value The value to apply the expression to.
     * @param unless Whether the expression should be inverted.
     * @return Whether the value is valid according to the expression.
     */
    open fun apply(value: Any, unless: Boolean): Boolean {
        return when (value) {
            is String -> value in (if (unless) declinedStringValues else acceptedStringValues)
            is Number -> value.toInt() == (if (unless) 0 else 1)
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported, create your own expression")
        }
    }
}