package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.PathConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [PathConstraint::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PathValidation(
    val message: String = "{spring-rules.path}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)