package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.Path

class PathConstraint: SimpleConstraint<Path, String>() {
    override fun isValid(path: String): Boolean {
        if (path.isEmpty()) {
            return false
        }

        return path.matches("^/([a-zA-Z0-9-_]+/)*[a-zA-Z0-9-_]+$".toRegex())
    }
}