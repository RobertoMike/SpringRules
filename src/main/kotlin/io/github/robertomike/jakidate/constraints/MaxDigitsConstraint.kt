package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.MaxDigits

class MaxDigitsConstraint: SimpleConstraint<MaxDigits, Number>() {

    override fun isValid(value: Number): Boolean {
        return value.toString().replace(".", "").length <= annotation.value
    }
}