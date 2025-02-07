package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Contain
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A constraint that checks if a value is contained in a list of allowed values.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class ContainConstraint : SimpleConstraint<Contain, Any>() {
    /**
     * Checks if the given value is valid according to the constraint.
     *
     * @param value the value to check
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is String -> searchBy { value.equals(it, ignoreCase = true) }

            is Double -> searchBy { value == it.toDouble() }
            is Float -> searchBy { value == it.toFloat() }

            is Int, is Long, is BigInteger, is BigDecimal, is Short, is Byte ->
                searchBy { value.toString() == it }

            is Map<*, *> -> searchBy { value.containsKey(it) }

            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }

    private fun searchBy(predicate: (String) -> Boolean): Boolean {
        return when (annotation.operator) {
            Contain.Operator.OR -> annotation.value.any(predicate)
            Contain.Operator.AND -> annotation.value.all(predicate)
        }
    }
}