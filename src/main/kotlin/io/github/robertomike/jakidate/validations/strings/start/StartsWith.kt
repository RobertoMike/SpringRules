package io.github.robertomike.jakidate.validations.strings.start

import io.github.robertomike.jakidate.constraints.strings.start.StartsWithConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * The string must start with the specified value
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [StartsWithConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class StartsWith(
    /**
     * the value the string must start with
     */
    val value: String,
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.start.starts-with}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated with the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
