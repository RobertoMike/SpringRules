package io.github.robertomike.springrules.constraints

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

@FunctionalInterface
interface SimpleConstraintValidator<A : Annotation, T> : ConstraintValidator<A, T> {
    override fun initialize(constraintAnnotation: A) {
    }

    override fun isValid(value: T?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        return isValid(value)
    }

    fun isValid(value: T): Boolean
}