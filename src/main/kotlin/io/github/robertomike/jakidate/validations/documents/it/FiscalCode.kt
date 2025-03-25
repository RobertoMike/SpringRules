package io.github.robertomike.jakidate.validations.documents.it

import io.github.robertomike.jakidate.constraints.it.FiscalCodeConstraint
import javax.validation.Constraint
import javax.validation.OverridesAttribute
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import javax.validation.constraints.Pattern.Flag
import kotlin.reflect.KClass

/**
 * Validates a string is an Italian Fiscal Code using a regex and a process of validation through checksum
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [FiscalCodeConstraint::class])
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
@Pattern(regexp = "^[A-Z]{6}\\d{2}[ABCDEHLMPRST]\\d{2}[A-Z]\\d{3}[A-Z]\$", message = "{jakidate.document.it.fiscal-code}")
annotation class FiscalCode(
    /**
     * These are flags to control the pattern matching
     */
    @get:OverridesAttribute(constraint = Pattern::class, name = "flags")
    val flags: Array<Flag> = [Flag.CASE_INSENSITIVE],
    /**
     * the error message template
     */
    val message: String = "{jakidate.document.it.fiscal-code}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
