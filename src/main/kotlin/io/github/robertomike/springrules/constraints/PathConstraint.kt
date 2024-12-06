package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.PathValidation

class PathConstraint: SimpleConstraint<PathValidation, String>() {
    override fun isValid(path: String): Boolean {
        if (path.isEmpty()) {
            return false
        }

        return path.matches("^/([a-zA-Z0-9-_]+/)*[a-zA-Z0-9-_]+$".toRegex())
    }
}