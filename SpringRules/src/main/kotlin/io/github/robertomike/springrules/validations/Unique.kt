package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.UniqueConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [UniqueConstraint::class])
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
annotation class Unique(
    val message: String = "{spring-rules.unique}",
    val method: String,
    val repository: KClass<*>,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)