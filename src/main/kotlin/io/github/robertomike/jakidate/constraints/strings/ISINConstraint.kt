package io.github.robertomike.jakidate.constraints.strings

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.documents.us.ISIN

/**
 * A constraint validator that checks if a given string is a valid International Securities Identification Number (ISIN).
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ISINConstraint : SimpleConstraint<ISIN, String>() {
    /**
     * Checks if the given string is a valid ISIN.
     *
     * A valid ISIN consists of 12 characters, with the last character being a check digit.
     * The check digit is calculated using a specific algorithm.
     *
     * @param value the string to be validated
     * @return true if the string is a valid ISIN, false otherwise
     */
    override fun isValid(value: String): Boolean {
        if (!value.last().isDigit())
            return false

        // This functions convert numbers greater than 9 into two separate numbers, e.g. 10 -> 1 0
        val transformBigNumber = { it: Int -> it.toString().map { it.toString().toInt() } }

        val digits = value.dropLast(1)
            .flatMap { char ->
                when {
                    char.isDigit() -> listOf(char.toString().toInt())
                    char.isLetter() -> transformBigNumber(char.code - 55)
                    else -> return false
                }
            }

        var firstGroup = mutableListOf<Int>()
        var secondGroup = mutableListOf<Int>()
        digits.forEachIndexed { index, el ->
            if (index % 2 == 1)
                secondGroup.add(el)
            else
                firstGroup.add(el)
        }

        if (firstGroup.size > secondGroup.size)
            firstGroup = firstGroup.asSequence().flatMap { transformBigNumber(it * 2) }.toMutableList()
        else
            secondGroup = secondGroup.asSequence().flatMap { transformBigNumber(it * 2) }.toMutableList()

        val sum = firstGroup.sum() + secondGroup.sum()

        val checkDigit = (10 - (sum % 10)) % 10
        return checkDigit == value.last().toString().toInt()
    }
}