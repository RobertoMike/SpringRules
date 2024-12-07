package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.AlphaConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [AlphaConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Alpha(
    val message: String = "{spring-rules.alpha}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)