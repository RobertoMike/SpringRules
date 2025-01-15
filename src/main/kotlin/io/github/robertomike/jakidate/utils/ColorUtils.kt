package io.github.robertomike.jakidate.utils

/**
 * Regular expression pattern for validating hex color strings.
 *
 * This pattern matches hex color strings in the following formats:
 * - #RRGGBB (6 characters)
 * - #RRGGBBAA (8 characters)
 * - RGB (3 characters)
 * - RGBA (4 characters)
 *
 * @see [hexColorRegex]
 */
const val hexColorRegex = "^#([a-fA-F0-9]{3}|[a-fA-F0-9]{4}|[a-fA-F0-9]{6}|[a-fA-F0-9]{8})$"

/**
 * Regular expression pattern for validating RGB color strings in the legacy format.
 *
 * This pattern matches RGB color strings in the format "rgb(R, G, B)" or "rgb(R, G, B, a)".
 * The R, G, and B values can be integers or percentages.
 * The a value is optional and can be a percentage, a number from 0 to 1 or omitted.
 *
 * @see [rgbLegacyRegex]
 */
const val rgbLegacyRegex = "^rgb(a?)\\(?([01]?\\d\\d?|2[0-4]\\d|25[0-5])(,\\s?)([01]?\\d\\d?|2[0-4]\\d|25[0-5])(,\\s?)(([01]?\\d\\d?|2[0-4]\\d|25[0-5])((,\\s?)(\\d\\d%|100%|0%|0|1|0\\.\\d\\d?))?\\)?)$"
/**
 * Regular expression pattern for validating RGB color strings in the legacy format with percentage values.
 *
 * This pattern matches RGB color strings in the format "rgb(R% G% B%)" or "rgb(R% G% B% a)".
 * The R, G, and B values can be integers or percentages.
 * The a value is optional and can be a percentage, a number from 0 to 1 or omitted.
 *
 * @see [rgbLegacyPercentageRegex]
 */
const val rgbLegacyPercentageRegex = "^rgb(a?)\\(?(\\d?\\d%|100%)(,\\s?)(\\d?\\d%|100%|0%)(,\\s?)((\\d?\\d%|100%)((,\\s?)(\\d?\\d%|100%|0|1|0\\.\\d\\d?))?\\)?)\$"
/**
 * Regular expression pattern for validating RGB color strings in the new format.
 *
 * This pattern matches RGB color strings in the format "rgb(R G B)" or "rgb(R G B, a)".
 * The R, G, and B values can be integers or percentages.
 * The a value is optional and can be a percentage, a number from 0 to 1 or omitted.
 *
 * @see [rgbRegex]
 */
const val rgbRegex = "^rgb\\(?([01]?\\d\\d?|2[0-4]\\d|25[0-5])(\\s)([01]?\\d\\d?|2[0-4]\\d|25[0-5])(\\s)(([01]?\\d\\d?|2[0-4]\\d|25[0-5])((\\s\\/\\s)(\\d\\d%|100%|0%|0|1|0\\.\\d\\d?))?\\)?)\$"
/**
 * Regular expression pattern for validating RGB color strings in the new format with percentage values.
 *
 * This pattern matches RGB color strings in the format "rgb(R% G% B%)" or "rgb(R% G% B% / a)".
 * The R, G, and B values can be integers or percentages.
 * The a value is optional and can be a percentage, a number from 0 to 1 or omitted.
 *
 * @see [rgbPercentageRegex]
 */
const val rgbPercentageRegex = "^rgb\\(?(\\d?\\d%|100%)(\\s)(\\d?\\d%|100%|0%)(\\s)((\\d?\\d%|100%)((\\s\\/\\s)(\\d?\\d%|100%|0|1|0\\.\\d\\d?))?\\)?)$"

/**
 * Regular expression pattern for validating HSL color strings.
 *
 * This pattern matches HSL color strings in the format "hsl(H, S, L)" or "hsla(H, S, L, A)".
 * The H value can be an angle in degrees, gradians, radians, or turns.
 * The S and L values can be percentages.
 * The 'A' value is optional and can be a percentage or omitted.
 *
 * @see [hslRegex]
 */
const val hslRegex = "hsla?\\(\\s*(?:(?:[12]?[0-9]{1,2}|3(?:[0-5][0-9]|60))|(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:deg|grad|rad|turn))\\s*,\\s*(?:100|[1-9]?[0-9])(?:\\.[0-9]+)?%\\s*,\\s*(?:100|[1-9]?[0-9])(?:\\.[0-9]+)?%(?:\\s*,\\s*(?:0(?:\\.[0-9]+)?|1(?:\\.0+)?))?\\s*\\)"
/**
 * Regular expression pattern for validating OKLCH color strings.
 *
 * This pattern matches OKLCH color strings in the format "oklch(L C H)" or "oklch(L C H / a)".
 * The L value can be a percentage.
 * The C and H values can be numbers.
 * The a value is optional and can be a percentage or omitted.
 *
 * @see [oklchRegex]
 */
const val oklchRegex = "oklch\\(\\s*(?:(?:100|[1-9]?[0-9])(?:\\.[0-9]+)?%|\\d*\\.?\\d+)\\s+(?:\\d*\\.?\\d+)\\s+(?:(?:360|(?:3[0-5][0-9]|[12][0-9][0-9]|[1-9]?[0-9])(?:\\.[0-9]+)?)|(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:deg|grad|rad|turn))(?:\\s*\\/\\s*(?:0(?:\\.[0-9]+)?|1(?:\\.0+)?))?\\s*\\)"