package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.EndsWith

class EndsWithConstraint: SimpleConstraint<EndsWith, String>() {
    override fun isValid(value: String): Boolean {
        if (value.isEmpty()) {
            return false
        }

        return value.endsWith(annotation.value)
    }
}