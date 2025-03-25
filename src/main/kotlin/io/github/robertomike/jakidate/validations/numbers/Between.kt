package io.github.robertomike.jakidate.validations.numbers

import io.github.robertomike.jakidate.constraints.numbers.BetweenConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating that a number is between a minimum and maximum value.
 *
 * This annotation can be used to validate that a number is between a minimum and maximum value.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [BetweenConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Between(
    /**
     * the minimum value
     */
    val min: String,
    /**
     * the maximum value
     */
    val max: String,
    /**
     * The format used to parse the date
     */
    val format: String = "YYYY-MM-DD",
    /**
     * the error message template
     */
    val message: String = "{jakidate.numeric.between}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
