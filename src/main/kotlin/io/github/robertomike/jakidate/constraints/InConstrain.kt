package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.In

class InConstrain : SimpleConstraint<In, String>() {
    override fun isValid(value: String): Boolean {
        return annotation.allowed
            .any { value.equals(it, ignoreCase = true) }
    }
}