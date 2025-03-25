package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.utils.oklchRegex
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * Validates a string is an oklch color
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@ReportAsSingleViolation
@Pattern(regexp = oklchRegex, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{jakidate.colors.oklch}")
annotation class OklchColor(
    /**
     * the error message template
     */
    val message: String = "{jakidate.colors.oklch}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)

