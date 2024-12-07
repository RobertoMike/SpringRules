package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.Password

class PasswordConstraint: SimpleMessageConstraint<Password, String>() {
    private val symbolRegex = "(?=.*[~!@#$%^&*()_-]).*".toRegex()
    private val lowercaseRegex = "(?=.*[a-z]).*".toRegex()
    private val uppercaseRegex = "(?=.*[A-Z]).*".toRegex()
    private val letterRegex = ".*[a-zA-Z].*".toRegex()
    private val digitRegex = "(?=.*\\d).*".toRegex()

    override fun isValid(value: String, util: MessageUtil): Boolean {
        val messages = mutableListOf<String>()

        if (value.length < annotation.minLength) {
            messages.add("password.length.min")
        }

        if (value.length > annotation.maxLength) {
            messages.add("password.length.max")
        }

        if (annotation.specialCharacters && !value.matches(symbolRegex)) {
            messages.add("password.special-character")
        }

        if (annotation.digit && !value.matches(digitRegex)) {
            messages.add("password.digit")
        }

        if (annotation.letters && annotation.uppercaseAndLowercase) {
            // Todo: we need to check if in this way will add the two messages
            when {
                !value.matches(lowercaseRegex) -> messages.add("password.lower-case")
                !value.matches(uppercaseRegex) -> messages.add("password.upper-case")
            }
        }

        if (annotation.letters && !value.matches(letterRegex)) {
            messages.add("password.letter")
        }

        if (messages.isNotEmpty()) {
            util.changeMessages(*messages.toTypedArray())
            return false
        }

        return true
    }
}