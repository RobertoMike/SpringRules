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
    val message: String = "{spring-rules.password}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)