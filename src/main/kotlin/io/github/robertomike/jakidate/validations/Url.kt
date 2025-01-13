package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.UrlConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotNull
import kotlin.reflect.KClass

/**
 * Annotation to validate that a field of type String.

 * This annotation can be used to validate that a URL is in a correct format. It uses the [UrlConstraint] to perform the validation.

 * @property message The error message to display if the URL is not valid. Defaults to "{spring-rules.url}".
 * @property groups The groups to which this constraint belongs. Defaults to an empty array.
 * @property payload The payload types to which this constraint belongs. Defaults to an empty array.
 *
 * @see UrlConstraint
 * @see jakarta.validation.Constraint
 * @see jakarta.validation.Payload
 */
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
annotation class Url(
    val message: String = "{jakidate.url}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

