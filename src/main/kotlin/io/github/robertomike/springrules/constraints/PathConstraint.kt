package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.Path

class PathConstraint: SimpleConstraint<Path, String>() {
    override fun isValid(value: String): Boolean {
        if (value.isEmpty()) {
            return false
        }

        return value.matches("^/([a-zA-Z0-9-_]+/)*[a-zA-Z0-9-_]+$".toRegex())
    }
}