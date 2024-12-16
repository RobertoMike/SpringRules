package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.MaxDigitsConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [MaxDigitsConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class MaxDigits(
    val value: Int,
    val message: String = "{jakidate.max-digits}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
