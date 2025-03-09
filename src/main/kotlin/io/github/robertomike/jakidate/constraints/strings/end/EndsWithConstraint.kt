package io.github.robertomike.jakidate.constraints.strings.end

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.end.EndsWith

/**
 * A constraint that checks if a string ends with a specified value.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class EndsWithConstraint: SimpleConstraint<EndsWith, String>() {
    /**
     * Checks if the given string ends with the value specified in the annotation.
     *
     * @param value the string to be checked
     * @return true if the string ends with the specified value, false otherwise
     */
    override fun isValid(value: String): Boolean {
        return value.endsWith(annotation.value)
    }
}