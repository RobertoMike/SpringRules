package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.toDate
import io.github.robertomike.jakidate.utils.toLocalDateTime
import io.github.robertomike.jakidate.validations.Between
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDateTime
import java.util.Date

/**
 * A constraint that checks if a value is between a minimum and maximum value.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class BetweenConstraint: SimpleConstraint<Between, Any>() {

    /**
     * Checks if a value is between a minimum and maximum value.
     *
     * @param value the value to check
     * @return true if the value is between the minimum and maximum value, false otherwise
     * @throws UnsupportedOperationException if the type of the value is not supported
     */
    override fun isValid(value: Any): Boolean {
        val min = annotation.min
        val max = annotation.max
        val format = annotation.format

        return when (value) {
            is Int -> value in min.toInt()..max.toInt()
            is Long -> value in min.toLong()..max.toLong()
            is Double -> value in min.toDouble()..max.toDouble()
            is Float -> value in min.toFloat()..max.toFloat()
            is BigInteger -> value in min.toBigInteger()..max.toBigInteger()
            is BigDecimal -> value in min.toBigDecimal()..max.toBigDecimal()
            is Short -> value in min.toShort()..max.toShort()
            is Byte -> value in min.toByte()..max.toByte()
            is Date -> value in min.toDate(format)..max.toDate(format)
            is LocalDateTime -> value in min.toLocalDateTime()..max.toLocalDateTime()
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}