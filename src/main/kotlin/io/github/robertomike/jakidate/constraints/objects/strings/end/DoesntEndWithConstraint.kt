package io.github.robertomike.jakidate.constraints.objects.strings.end

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.EndsWith

class DoesntEndWithConstraint: SimpleConstraint<EndsWith, String>() {
    override fun isValid(value: String): Boolean {
        return !value.endsWith(annotation.value)
    }
}