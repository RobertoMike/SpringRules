package io.github.robertomike.jakidate.enums

import io.github.robertomike.jakidate.utils.rgbLegacyRegex
import io.github.robertomike.jakidate.utils.rgbRegex

/**
 * Enum class representing different RGB options.
 *
 * This enum provides different regular expression patterns for validating RGB values.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
enum class RgbOptions {
    /**
     * Legacy RGB format (e.g. "rgb(0,0,0)" or "rgba(0,0,0,1)").
     */
    LEGACY,
    /**
     * New RGB format (e.g. "rgb(0 0 0)" or "rgba(0 0 0, 1)").
     */
    NEW,
    /**
     * All the precedent RGB formats.
     */
    ALL;

    /**
     * Returns the regular expression pattern(s) associated with this RGB option.
     *
     * @return a list of regular expression patterns
     */
    val regex: List<String>
        get() = when (this) {
            LEGACY -> listOf(rgbLegacyRegex)
            NEW -> listOf(rgbRegex)
            ALL -> listOf(rgbLegacyRegex, rgbRegex)
        }
}