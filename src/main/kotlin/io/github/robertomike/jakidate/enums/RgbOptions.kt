package io.github.robertomike.jakidate.enums

import io.github.robertomike.jakidate.utils.rgbLegacyPercentageRegex
import io.github.robertomike.jakidate.utils.rgbLegacyRegex
import io.github.robertomike.jakidate.utils.rgbPercentageRegex
import io.github.robertomike.jakidate.utils.rgbRegex

enum class RgbOptions {
    LEGACY,
    LEGACY_PERCENTAGE,
    NEW,
    NEW_PERCENTAGE,
    ALL;

    val regex: List<String>
        get() = when (this) {
            LEGACY -> listOf(rgbLegacyRegex)
            LEGACY_PERCENTAGE -> listOf(rgbLegacyPercentageRegex)
            NEW -> listOf(rgbRegex)
            NEW_PERCENTAGE -> listOf(rgbPercentageRegex)
            ALL -> listOf(rgbLegacyRegex, rgbLegacyPercentageRegex, rgbRegex, rgbPercentageRegex)
        }
}