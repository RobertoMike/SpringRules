package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Alpha

class AlphaConstraint: SimpleConstraint<Alpha, String>() {
    private val letterRegex = ".*[a-zA-Z].*".toRegex()

    override fun isValid(value: String): Boolean {
        return value.matches(letterRegex)
    }
}