package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.MinDigitsConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [MinDigitsConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class MinDigits(
    val value: Int,
    val message: String = "{jakidate.digits.min}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
