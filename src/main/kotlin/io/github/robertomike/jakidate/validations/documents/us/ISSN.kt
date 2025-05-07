package io.github.robertomike.jakidate.validations.documents.us

import io.github.robertomike.jakidate.constraints.strings.ISSNConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * Validates a string is an ISSN
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
@Constraint(validatedBy = [ISSNConstraint::class])
@ReportAsSingleViolation
@Pattern(regexp = "^(\\d{4}-\\d{3}[Xx0-9])$", message = "{jakidate.documents.us.issn}")
annotation class ISSN(
    /**
     * the error message template
     */
    val message: String = "{jakidate.documents.us.issn}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated with the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)