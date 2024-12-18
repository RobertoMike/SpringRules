package io.github.robertomike.jakidate.constraints.objects.strings.start

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.strings.DoesntStartWith

class DoesntStartWithConstraint: SimpleConstraint<DoesntStartWith, String>() {
    override fun isValid(value: String): Boolean {
        return !value.startsWith(annotation.value)
    }
}