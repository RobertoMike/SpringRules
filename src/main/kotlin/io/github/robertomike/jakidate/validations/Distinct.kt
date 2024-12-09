package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.DistinctConstraint
import io.github.robertomike.jakidate.constraints.required.RequiredIfConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [DistinctConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Distinct(
    val message: String = "{spring-rules.distinct}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
