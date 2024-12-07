package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.PathConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [PathConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Path(
    val message: String = "{spring-rules.path}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)