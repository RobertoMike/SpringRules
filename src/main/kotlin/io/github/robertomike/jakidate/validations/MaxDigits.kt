package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.MaxDigitsConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating that a number has a maximum number of digits.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [MaxDigitsConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class MaxDigits(
    val value: Int,
    /**
     * the error message template
     */
    val message: String = "{jakidate.digits.max}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
