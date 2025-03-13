package io.github.robertomike.jakidate.constraints.web

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.web.Url
import java.net.URL

/**
 * A constraint validator that checks if a given string is a valid URL.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class UrlConstraint : SimpleConstraint<Url, String>() {
    /**
     * Checks if the given string is a valid URL.
     *
     * @param value the string to be validated
     * @return true if the string is a valid URL, false otherwise
     */
    override fun isValid(value: String): Boolean {
        try {
            URL(value).toURI()
            return true
        } catch (e: Exception) {
            return false
        }
    }
}