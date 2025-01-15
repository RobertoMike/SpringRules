package io.github.robertomike.jakidate.constraints.it

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.specialToMap
import io.github.robertomike.jakidate.validations.it.FiscalCode

/**
 * A custom constraint validator for Italian fiscal codes.
 *
 * This class provides a validation mechanism for Italian fiscal codes, ensuring that they conform to the expected format and rules.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class FiscalCodeConstraint : SimpleConstraint<FiscalCode, String>() {
    companion object {
        /**
         * A map of even values used in the calculation of the fiscal code control character.
         *
         * This map is initialized with the values from the {@link #evenString} constant.
         *
         */
        private val evenValues: Map<String, Int> = evenString.specialToMap()
        /**
         * A map of odd values used in the calculation of the fiscal code control character.
         *
         * This map is initialized with the values from the {@link #oddString} constant.
         */
        private val oddValues: Map<String, Int> = oddString.specialToMap()
    }

    /**
     * Validates the given fiscal code.
     *
     * This method checks if the provided fiscal code is valid according to the Italian fiscal code rules.
     *
     * @param value the fiscal code to validate
     * @return true if the fiscal code is valid, false otherwise
     */
    override fun isValid(value: String): Boolean {
        var sum = 0
        for (i in 0..14) {
            val character = value[i].toString()
            val number = if (i % 2 == 0) oddValues[character] else evenValues[character]

            sum += number ?: throw RulesException("Invalid character: $character")
        }

        val controlChar = ('A' + (sum % 26))

        return controlChar == value.last()
    }
}

/**
 * A string constant representing the possible values for calculating the control character for odd positions.
 */
const val oddString = "0|1\\1|0\\2|5\\3|7\\4|9\\5|13\\6|15\\7|17\\8|19\\9|21\\A|1\\B|0\\C|5\\D|7\\E|9\\F|13\\G|15\\H" +
        "|17\\I|19\\J|21\\K|2\\L|4\\M|18\\N|20\\O|11\\P|3\\Q|6\\R|8\\S|12\\T|14\\U|16\\V|10\\W|22\\X|25\\Y|24\\Z|23"
/**
 * A string constant representing the possible values for calculating the control character for even positions.
 */
const val evenString = "0|0\\1|1\\2|2\\3|3\\4|4\\5|5\\6|6\\7|7\\8|8\\9|9\\A|0\\B|1\\C|2\\D|3\\E|4\\F|5\\G|6\\H|7\\I" +
        "|8\\J|9\\K|10\\L|11\\M|12\\N|13\\O|14\\P|15\\Q|16\\R|17\\S|18\\T|19\\U|20\\V|21\\W|22\\X|23\\Y|24\\Z|25"