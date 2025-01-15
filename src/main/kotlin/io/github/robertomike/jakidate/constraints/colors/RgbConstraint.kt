package io.github.robertomike.jakidate.constraints.colors

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.colors.RgbColor

/**
 * A constraint for validating RGB color strings.
 *
 * This class represents a constraint that validates RGB color strings based on the provided regular expressions.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class RgbConstraint : SimpleConstraint<RgbColor, String>() {
    /**
     * Checks if the provided RGB color string is valid.
     *
     * This method checks if the provided value matches any of the regular expressions provided in the annotation.
     *
     * @param value the RGB color string to be validated
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: String): Boolean {
        return annotation.value.regex.any { value.matches(it.toRegex()) }
    }
}