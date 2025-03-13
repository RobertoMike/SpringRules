package io.github.robertomike.jakidate.validations.strings.cases

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * The string must be screaming snake case
 *
 * An example is: ACCEPT_LANGUAGE, HELLO_WORLD
 *
 * @author Giorgio Andrei
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
@ReportAsSingleViolation
@Pattern(regexp = "^(([A-Z0-9]+)(_)*)+$", message = "{jakidate.strings.case.screaming-snake}")
annotation class ScreamingSnakeCase(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.case.screaming-snake}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)