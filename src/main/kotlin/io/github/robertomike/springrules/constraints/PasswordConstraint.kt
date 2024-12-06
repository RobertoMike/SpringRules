package io.github.robertomike.springrules.constraints

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import io.github.robertomike.springrules.validations.PasswordValidation

class PasswordConstraint : ConstraintValidator<PasswordValidation, String?> {
    private var canBeEmpty = false

    override fun initialize(passwordConstrain: PasswordValidation) {
        this.canBeEmpty = passwordConstrain.canBeEmpty
    }

    override fun isValid(password: String?, validatorContext: ConstraintValidatorContext): Boolean {
        if (canBeEmpty && (password == null || password.isEmpty())) {
            return true
        }

        /* Password needs to be as long as required, contains at least one digit,
         one lowercase letter, one uppercase letter and one special character */
        return password!!.length >= 8
                && password.matches("(?=.*\\d).*".toRegex())
                && password.matches("(?=.*[a-z]).*".toRegex())
                && password.matches("(?=.*[A-Z]).*".toRegex())
                && password.matches("(?=.*[~!@#$%^&*()_-]).*".toRegex())
    }
}