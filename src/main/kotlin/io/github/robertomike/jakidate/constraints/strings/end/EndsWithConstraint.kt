package io.github.robertomike.jakidate.constraints.strings.end

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.EndsWith

class EndsWithConstraint: SimpleConstraint<EndsWith, String>() {
    override fun isValid(value: String): Boolean {
        return value.endsWith(annotation.value)
    }
}