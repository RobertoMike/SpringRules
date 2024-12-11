package io.github.robertomike.jakidate.constraints.objects.string.end

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.EndsWith

class EndsWithConstraint: SimpleConstraint<EndsWith, String>() {
    override fun isValid(value: String): Boolean {
        return value.endsWith(annotation.value)
    }
}