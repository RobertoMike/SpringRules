package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.ExistsConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ExistsConstraint::class])
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
annotation class Exists(
    val repository: KClass<*>,
    val method: String = "findById",
    val message: String = "{spring-rules.exists}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)