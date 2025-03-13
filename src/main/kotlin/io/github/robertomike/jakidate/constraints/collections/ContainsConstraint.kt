package io.github.robertomike.jakidate.constraints.collections

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.collections.Contains
import java.math.BigDecimal
import java.math.BigInteger

/**
 * A constraint that checks if a given value is contained in a list of allowed values.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class ContainsConstraint : SimpleConstraint<Contains, Any>() {
    /**
     * Checks if the given value is valid according to the constraint.
     *
     * @param value the value to check
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is String -> searchBy { value.equals(it, ignoreCase = true) }
            is Int, is Long, is BigInteger, is BigDecimal, is Short, is Byte ->
                searchBy { value.toString() == it }
            is Double -> searchBy { value == it.toDouble() }
            is Float -> searchBy { value == it.toFloat() }
            is Map<*, *> -> searchBy { value.containsKey(it) }
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }

    private fun searchBy(predicate: (String) -> Boolean): Boolean {
        return when (annotation.operator) {
            Contains.Operator.OR -> annotation.value.any(predicate)
            Contains.Operator.AND -> annotation.value.all(predicate)
        }
    }
}