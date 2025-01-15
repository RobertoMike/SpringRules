package io.github.robertomike.jakidate.constraints.strings.start

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.StartsWith

/**
 * A constraint that checks if a string starts with a specified value.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class StartsWithConstraint: SimpleConstraint<StartsWith, String>() {
    /**
     * Checks if the given string starts with the value specified in the annotation.
     *
     * @param value the string to check
     * @return true if the string starts with the specified value, false otherwise
     */
    override fun isValid(value: String): Boolean {
        return value.startsWith(annotation.value)
    }
}