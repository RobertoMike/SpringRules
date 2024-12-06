package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.PasswordConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PasswordConstraint::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordValidation(
    val message: String = "{spring-rules.password.default}",
    val minLength: Int = 8,
    val maxLength: Int = 256,
    val number: Boolean = true,
    val letters: Boolean = true,
    val uppercaseAndLowercase: Boolean = true,
    val specialCharacters: Boolean = true,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)