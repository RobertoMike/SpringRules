package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.rgbLegacyPercentageRegex
import io.github.robertomike.jakidate.utils.rgbLegacyRegex
import io.github.robertomike.jakidate.utils.rgbPercentageRegex
import io.github.robertomike.jakidate.utils.rgbRegex
import io.github.robertomike.jakidate.validations.colors.RgbColor

class RgbConstraint : SimpleConstraint<RgbColor, String>() {
    override fun isValid(value: String): Boolean {
        return value.matches(rgbLegacyRegex.toRegex()) ||
                value.matches(rgbLegacyPercentageRegex.toRegex()) ||
                value.matches(rgbRegex.toRegex()) ||
                value.matches(rgbPercentageRegex.toRegex())
    }
}