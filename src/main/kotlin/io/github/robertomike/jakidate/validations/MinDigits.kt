package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.MinDigitsConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating the minimum number of digits in a number.
 *
 * This annotation can be used to validate the minimum number of digits in a number.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [MinDigitsConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class MinDigits(
    val value: Int,
    /**
     * the error message template
     */
    val message: String = "{jakidate.digits.min}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
