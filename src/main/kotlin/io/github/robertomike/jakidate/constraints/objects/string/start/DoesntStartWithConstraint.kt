package io.github.robertomike.jakidate.constraints.objects.string.start

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.StartsWith

class DoesntStartWithConstraint: SimpleConstraint<StartsWith, String>() {
    override fun isValid(value: String): Boolean {
        return !value.startsWith(annotation.value)
    }
}