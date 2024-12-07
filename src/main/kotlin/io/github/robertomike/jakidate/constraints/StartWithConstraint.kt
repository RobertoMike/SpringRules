package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.StartsWith

class StartWithConstraint: SimpleConstraint<StartsWith, String>() {
    override fun isValid(value: String): Boolean {
        if (value.isEmpty()) {
            return false
        }

        return value.startsWith(annotation.value)
    }
}