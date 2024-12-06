package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.EndWithValidation

class EndWithConstraint: SimpleConstraint<EndWithValidation, String>() {
    override fun isValid(value: String): Boolean {
        if (value.isEmpty()) {
            return false
        }

        return value.endsWith(annotation.value)
    }
}