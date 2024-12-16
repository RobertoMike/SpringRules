package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.MinDigits

class MinDigitsConstraint: SimpleConstraint<MinDigits, Number>() {

    override fun isValid(value: Number): Boolean {
        return value.toString().replace(".", "").length >= annotation.value
    }
}