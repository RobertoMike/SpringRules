package io.github.robertomike.jakidate.validations.web

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * Annotation for validating a path.
 *
 * This annotation can be used to validate a path in a variety of contexts, such as a URL path or a file path.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Constraint(validatedBy = [])
@ReportAsSingleViolation
@Pattern(regexp = "^/([a-zA-Z0-9_\\-!\"£$%&'^.,;:#]+)*(/[a-zA-Z0-9_\\-!\"£$%&'^.,;:#]+)*$", message = "{jakidate.web.relative-path}")
annotation class RelativePath(
    /**
     * the error message template
     */
    val message: String = "{jakidate.web.relative-path}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)