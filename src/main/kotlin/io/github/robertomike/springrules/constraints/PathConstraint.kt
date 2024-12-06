package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.PathValidation
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext


class PathConstraint : ConstraintValidator<PathValidation?, String?> {
    override fun initialize(constrain: PathValidation?) {
    }

    override fun isValid(path: String?, validatorContext: ConstraintValidatorContext): Boolean {
        if (path == null || path.isEmpty()) {
            return false
        }

        return path.matches("^/([a-zA-Z0-9-_]+/)*[a-zA-Z0-9-_]+$".toRegex())
    }
}