package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.toDate
import io.github.robertomike.jakidate.utils.toLocalDateTime
import io.github.robertomike.jakidate.validations.Between
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
        return when (value) {
            is Int, is Long -> value in annotation.min.toLong()..annotation.max.toLong()
            is Double -> value in annotation.min.toDouble()..annotation.max.toDouble()
            is Float -> value in annotation.min.toFloat()..annotation.max.toFloat()
            is Date -> value in annotation.min.toDate("YYYY-MM-DD")..annotation.max.toDate("YYYY-MM-DD")
            is LocalDateTime -> value in annotation.min.toLocalDateTime()..annotation.max.toLocalDateTime()
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}