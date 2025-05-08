package io.github.robertomike.jakidate.validations.strings.nullable

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import javax.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * This annotation can be used to validate that a field of type string, the validation passes if the field is not passed or when it is null, but if it is passed, it must not be blank.
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
@Pattern(regexp = "^(?!\\s*$).+", message = "{jakidate.strings.nullable.null-or-not-blank}")
annotation class NullOrNotBlank(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.nullable.null-or-not-blank}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated with the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)