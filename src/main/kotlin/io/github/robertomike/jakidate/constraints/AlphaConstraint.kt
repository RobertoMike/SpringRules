package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.Alpha

class AlphaConstraint: SimpleConstraint<Alpha, String>() {
    private val letterRegex = ".*[a-zA-Z].*".toRegex()

    override fun isValid(value: String): Boolean {
        return value.matches(letterRegex)
    }
}