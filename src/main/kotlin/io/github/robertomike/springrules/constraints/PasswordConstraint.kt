package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.PasswordValidation

class PasswordConstraint: SimpleConstraint<PasswordValidation, String>() {
    override fun isValid(password: String): Boolean {
        /* Password needs to be as long as required, contains at least one digit,
         one lowercase letter, one uppercase letter and one special character */
        return password.length >= 8
                && password.matches("(?=.*\\d).*".toRegex())
                && password.matches("(?=.*[a-z]).*".toRegex())
                && password.matches("(?=.*[A-Z]).*".toRegex())
                && password.matches("(?=.*[~!@#$%^&*()_-]).*".toRegex())
    }
}