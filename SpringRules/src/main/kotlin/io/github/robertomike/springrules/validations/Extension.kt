package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.ExtensionConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ExtensionConstraint::class])
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
annotation class Extension(
    val value: Array<String> = [],
    val message: String = "{spring-rules.file.extension}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)