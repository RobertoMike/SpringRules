package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.MultipleOf
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A constraint that checks if a value is a multiple of a given number.
 *
 * This constraint can be applied to properties of type [Int], [BigInteger], [BigDecimal], [Short], [Byte], and [Long].
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class MultipleOfConstraint : SimpleConstraint<MultipleOf, Any>() {
    /**
     * Checks if the given value is a multiple of the number specified in the [MultipleOf] annotation.
     *
     * @param value the value to validate
     * @return true if the value is a multiple of the specified number, false otherwise
     * @throws UnsupportedOperationException if the type of the value is not supported
     */
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Int -> value % annotation.value.toInt() == 0
            is BigInteger -> value % annotation.value.toBigInteger() == BigInteger.ZERO
            is BigDecimal -> value % annotation.value.toBigDecimal() == BigDecimal.ZERO
            is Short -> value % annotation.value.toShort() == 0
            is Byte -> value % annotation.value.toByte() == 0
            is Long -> value % annotation.value.toLong() == 0L
            is Double -> value % annotation.value.toDouble() == 0.0
            is Float -> value % annotation.value.toFloat() == 0.0f
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}