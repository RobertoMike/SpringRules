package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.PasswordConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PasswordConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Password(
    val message: String = "{jakidate.password.default}",
    val minLength: Int = 8,
    val maxLength: Int = 256,
    val digit: Boolean = true,
    val letters: Boolean = true,
    val uppercaseAndLowercase: Boolean = true,
    val specialCharacters: Boolean = true,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)