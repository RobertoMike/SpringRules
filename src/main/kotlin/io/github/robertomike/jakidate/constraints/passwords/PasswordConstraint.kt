package io.github.robertomike.jakidate.constraints.passwords

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.passwords.Password

/**
 * PasswordConstraint is a class that provides a custom validation constraint for passwords.
 * It validates the strength of a password against the specified requirements.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class PasswordConstraint: SimpleMessageConstraint<Password, String>() {
    /**
     * Regex pattern for special characters.
     */
    private val symbolRegex = "(?=.*[~!@#$%^&*()_-]).*".toRegex()
    /**
     * Regex pattern for lowercase letters.
     */
    private val lowercaseRegex = "(?=.*[a-z]).*".toRegex()
    /**
     * Regex pattern for uppercase letters.
     */
    private val uppercaseRegex = "(?=.*[A-Z]).*".toRegex()
    /**
     * Regex pattern for letters.
     */
    private val letterRegex = ".*[a-zA-Z].*".toRegex()
    /**
     * Regex pattern for digits.
     */
    private val digitRegex = "(?=.*\\d).*".toRegex()

    /**
     * Validates the given password against the specified requirements.
     *
     * @param value the password to validate
     * @param util the message util for error messages
     * @return true if the password is valid, false otherwise
     */
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