package io.github.robertomike.jakidate.validations

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * Annotation for validating a path.
 *
 * This annotation can be used to validate a path in a variety of contexts, such as a file path or a URL path.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
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
@Pattern(regexp = "^/(?:[a-zA-Z0-9_\\-!\"£$%&'^.,;:#]+)*(/[a-zA-Z0-9_\\-!\"£$%&'^.,;:#]+)*$", message = "{jakidate.path}")
annotation class Path(
    /**
     * the error message template
     */
    val message: String = "{jakidate.path}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)