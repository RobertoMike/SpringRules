package io.github.robertomike.jakidate.validations.numbers

import io.github.robertomike.jakidate.constraints.numbers.MultipleOfConstraint
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating that a value is a multiple of another value.
 *
 * This annotation can be used to validate that a value is a multiple of another value.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [MultipleOfConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class MultipleOf(
    /**
     * the number to which the value must be a multiple
     */
    val value: String,
    /**
     * the error message template
     */
    val message: String = "{jakidate.numeric.multiple-of}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
