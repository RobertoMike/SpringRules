package io.github.robertomike.jakidate.constraints.strings.start

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.start.DoesntStartWith

/**
 * A constraint that checks if a string does not start with a specified value.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class DoesntStartWithConstraint: SimpleConstraint<DoesntStartWith, String>() {
    /**
     * Checks if the given string does not start with the value specified in the annotation.
     *
     * @param value the string to check
     * @return true if the string does not start with the specified value, false otherwise
     */
    override fun isValid(value: String): Boolean {
        return !value.startsWith(annotation.value)
    }
}