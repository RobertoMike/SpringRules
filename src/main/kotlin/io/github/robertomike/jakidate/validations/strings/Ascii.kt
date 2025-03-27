package io.github.robertomike.jakidate.validations.strings

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * This annotation can be used to validate that ASCII code is in a correct format.
 *
 * @author Giorgio Andrei
 * @since 1.0.2
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
@Pattern(regexp = "^(?:[0-9]|[1-9][0-9]|1[0-1][0-9]|12[0-7])$", message = "{jakidate.strings.ascii}")
annotation class Ascii(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.ascii}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)