package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.PasswordConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import java.lang.annotation.ElementType
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [PasswordConstraint::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordValidation(
    val message: String = ("It needs to contain 1 uppercase letter, " +
            "1 lowercase letter, " +
            "1 digit, " +
            "1 special character and be 8 characters long."),
    val canBeEmpty: Boolean = false,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)