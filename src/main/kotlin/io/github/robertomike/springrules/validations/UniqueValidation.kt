package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.UniqueConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [UniqueConstraint::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class UniqueValidation(
    val message: String = "{spring-rules.unique}",
    val method: String,
    val repository: KClass<*>,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)