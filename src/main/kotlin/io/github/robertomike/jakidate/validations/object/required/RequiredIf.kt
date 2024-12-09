package io.github.robertomike.jakidate.validations.`object`.required

import io.github.robertomike.jakidate.constraints.required.RequiredIfConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [RequiredIfConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class RequiredIf(
    val message: String = "{spring-rules.required-if.default}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)