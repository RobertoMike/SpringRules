package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.StartsWith

class StartWithConstraint: SimpleConstraint<StartsWith, String>() {
    override fun isValid(value: String): Boolean {
        if (value.isEmpty()) {
            return false
        }

        return value.startsWith(annotation.value)
    }
}