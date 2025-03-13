package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.utils.hexColorRegex
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * Validates a string is a hex color
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [])
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
@ReportAsSingleViolation
@Pattern(regexp = hexColorRegex, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{jakidate.colors.hex}")
annotation class HexColor(
    /**
     * the error message template
     */
    val message: String = "{jakidate.colors.hex}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)

