package io.github.robertomike.jakidate.constraints.strings

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.ISSN

class ISSNConstraint : SimpleConstraint<ISSN, String>() {
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