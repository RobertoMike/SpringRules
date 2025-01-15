package io.github.robertomike.jakidate.constraints.strings

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.ISSN

/**
 * A constraint validator that checks if a given string is a valid ISSN (International Standard Serial Number).
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ISSNConstraint : SimpleConstraint<ISSN, String>() {
    /**
     * Checks if the given string is a valid ISSN.
     *
     * A valid ISSN consists of 8 digits, with the last digit being a check digit.
     * The check digit is calculated by multiplying each digit by its position (from right to left, starting at 8),
     * summing the results, and then subtracting the remainder of the sum divided by 11 from 11.
     *
     * @param value the string to be validated
     * @return true if the string is a valid ISSN, false otherwise
     */
    override fun isValid(value: String): Boolean {
        val onlyNumbers = value.replace("-", "")

        if (!onlyNumbers.all(Char::isDigit))
            return false

        val sum = onlyNumbers.dropLast(1)
            .asSequence()
            .mapIndexed { index, number ->
                number.toString().toInt() * (8 - index)
            }.sum()

        val checkDigit = 11 - (sum % 11)
        return checkDigit == value.last().toString().toInt()
    }
}