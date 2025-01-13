package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.MinDigits

/**
 * A constraint validator for the [MinDigits] annotation.
 *
 * This class checks if a given [Number] value has at least the minimum number of digits specified by the annotation.
 * @see MinDigits
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class MinDigitsConstraint: SimpleConstraint<MinDigits, Number>() {
    /**
     * Checks if the given [Number] value is valid according to the [MinDigits] constraint.
     *
     * @param value the value to validate
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: Number): Boolean {
        return value.toString().replace(".", "").length >= annotation.value
    }
}