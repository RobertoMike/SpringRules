package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Url
import java.net.URL;

class UrlConstraint : SimpleConstraint<Url, String>() {

    override fun isValid(value: String): Boolean {
        try {
            URL(value).toURI()
            return true
        } catch (e: Exception) {
            return false
        }
    }
}