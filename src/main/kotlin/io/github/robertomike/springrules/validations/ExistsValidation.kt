package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.ExistsConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ExistsConstraint::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExistsValidation(
    val message: String = "{spring-rules.exists}",
    val method: String,
    val repository: KClass<*>,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)