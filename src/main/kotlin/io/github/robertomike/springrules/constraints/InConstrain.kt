package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.In

class InConstrain : SimpleConstraint<In, String>() {
    override fun isValid(value: String): Boolean {
        return annotation.allowed
            .any { value.equals(it, ignoreCase = true) }
    }
}