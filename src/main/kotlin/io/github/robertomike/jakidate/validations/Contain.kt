package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.ContainConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ContainConstraint::class])
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
annotation class Contain(
    val value: Array<String>,
    val message: String = "{jakidate.contain}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)