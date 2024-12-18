package io.github.robertomike.jakidate.constraints.objects.colors

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.colors.RgbColor

class RgbConstraint : SimpleConstraint<RgbColor, String>() {
    override fun isValid(value: String): Boolean {
        return annotation.value.regex.any { value.matches(it.toRegex()) }
    }
}