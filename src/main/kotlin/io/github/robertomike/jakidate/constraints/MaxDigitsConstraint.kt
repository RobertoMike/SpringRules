package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.MaxDigits

/**
 * A constraint validator for the [MaxDigits] annotation.
 *
 * This class checks if a given [Number] value has the maximum number of digits specified by the annotation.
 *
 * @see MaxDigits
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class MaxDigitsConstraint: SimpleConstraint<MaxDigits, Number>() {
    /**
     * Checks if the given [Number] value is valid according to the [MaxDigits] constraint.
     *
     * @param value the value to validate
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: Number): Boolean {
        return value.toString().replace(".", "").length <= annotation.value
    }
}