package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.StartWithValidation

class StartWithConstraint: SimpleConstraint<StartWithValidation, String>() {
    override fun isValid(value: String): Boolean {
        if (value.isEmpty()) {
            return false
        }

        return value.startsWith(annotation.value)
    }
}