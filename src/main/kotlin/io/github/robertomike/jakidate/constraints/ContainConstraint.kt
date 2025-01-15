package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Contain

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
            is String -> annotation.value
                .any { value.equals(it, ignoreCase = true) }

            is Int, is Long, is Double, is Float -> annotation.value
                .any { value.toString() == it }

            is Map<*, *> -> annotation.value
                .any { value.containsKey(it) }

            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}