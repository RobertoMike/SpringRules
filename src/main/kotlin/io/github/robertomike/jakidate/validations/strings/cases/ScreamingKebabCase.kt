package io.github.robertomike.jakidate.validations.strings.cases

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * The string must be screaming kebab case
 *
 * An example is: ACCEPT-LANGUAGE, HELLO-WORLD
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Constraint(validatedBy = [])
@ReportAsSingleViolation
@Pattern(regexp = "^([A-Z0-9]+-?)+$", message = "{jakidate.strings.case.screaming-kebab}")
annotation class ScreamingKebabCase(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.case.screaming-kebab}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)