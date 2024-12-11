package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.UrlConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotNull
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [UrlConstraint::class])
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
annotation class Url(
    val message: String = "{jakidate.url}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

