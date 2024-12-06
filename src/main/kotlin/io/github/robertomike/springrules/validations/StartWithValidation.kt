package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.AcceptedConstraint
import io.github.robertomike.springrules.constraints.StartWithConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotNull
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [StartWithConstraint::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class StartWithValidation(
    val message: String = "{spring-rules.start-with}",
    val value: String,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
