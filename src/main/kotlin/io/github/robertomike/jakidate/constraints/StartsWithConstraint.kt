package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.StartsWith

class StartsWithConstraint: SimpleConstraint<StartsWith, String>() {
    override fun isValid(value: String): Boolean {
        return value.startsWith(annotation.value)
    }
}