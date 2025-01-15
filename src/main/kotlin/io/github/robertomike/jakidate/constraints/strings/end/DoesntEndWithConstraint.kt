package io.github.robertomike.jakidate.constraints.strings.end

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.DoesntEndWith

/**
 * A constraint that checks if a string doesn't end with a specified value.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class DoesntEndWithConstraint: SimpleConstraint<DoesntEndWith, String>() {
    /**
     * Checks if the given string doesn't end with the value specified in the annotation.
     *
     * @param value the string to be checked
     * @return true if the string ends with the specified value, false otherwise
     */
    override fun isValid(value: String): Boolean {
        return !value.endsWith(annotation.value)
    }
}