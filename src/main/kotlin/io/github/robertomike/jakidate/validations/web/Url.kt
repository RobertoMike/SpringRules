package io.github.robertomike.jakidate.validations.web

import io.github.robertomike.jakidate.constraints.web.UrlConstraint
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation to validate that a field of type String.
 * This annotation can be used to validate that a URL is in a correct format. It uses the [UrlConstraint] to perform the validation.
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [UrlConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
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

