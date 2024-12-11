package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.DeclinedConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotNull
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [DeclinedConstraint::class])
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
@NotNull
annotation class Declined(
    val message: String = "{jakidate.declined}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

