package io.github.robertomike.jakidate.validations.strings.nullable

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * This annotation can be used to validate that a field of type string, the validation passes if the field is not passed or when it is null, but if it is passed, it must not be empty.
 *
 * @author Giorgio Andrei
 * @since 1.0.7
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
@Pattern(regexp = "^.+$", message = "{jakidate.strings.nullable.null-or-not-empty}")
annotation class NullOrNotEmpty(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.nullable.null-or-not-empty}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated with the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)