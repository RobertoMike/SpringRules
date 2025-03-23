package io.github.robertomike.jakidate.validations.documents.us

import io.github.robertomike.jakidate.constraints.strings.ISINConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * Validates a string is an ISIN
 *
 * @author Roberto Micheletti
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
@Constraint(validatedBy = [ISINConstraint::class])
@ReportAsSingleViolation
@Pattern(regexp = "^([A-Z]{2}-?[0-9A-Z]{9}-?\\d)$", message = "{jakidate.strings.isin}")
annotation class ISIN(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.isin}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)