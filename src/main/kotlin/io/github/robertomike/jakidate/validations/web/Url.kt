package io.github.robertomike.jakidate.validations.web

import io.github.robertomike.jakidate.constraints.web.UrlConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
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
    /**
     * the error message template
     */
    val message: String = "{jakidate.url}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)

