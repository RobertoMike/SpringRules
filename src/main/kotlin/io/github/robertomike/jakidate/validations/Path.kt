package io.github.robertomike.jakidate.validations

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@MustBeDocumented
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
@Constraint(validatedBy = [])
@Pattern(regexp = "^/([a-zA-Z0-9-_]+/*)*[a-zA-Z0-9-_]+$", message = "{spring-rules.path}")
annotation class Path(
    val message: String = "{spring-rules.path}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)