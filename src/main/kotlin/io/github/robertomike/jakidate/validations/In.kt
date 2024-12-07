package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.InConstrain
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [InConstrain::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPE
)
@Retention(
    AnnotationRetention.RUNTIME
)
annotation class In(
    val allowed: Array<String>,
    val message: String = "{spring-rules.in}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)