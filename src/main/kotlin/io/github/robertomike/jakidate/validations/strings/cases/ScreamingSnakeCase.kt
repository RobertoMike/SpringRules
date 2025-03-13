package io.github.robertomike.jakidate.validations.strings.cases

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
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
    val message: String = "{jakidate.string.case.screaming-snake}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)