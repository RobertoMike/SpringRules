package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.constraints.colors.RgbConstraint
import io.github.robertomike.jakidate.enums.RgbOptions
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Validates a string is an RGB color
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [RgbConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class RgbColor(
    /**
     * This allows you what kind of structure you want
     */
    val value: RgbOptions = RgbOptions.ALL,
    /**
     * the error message template
     */
    val message: String = "{jakidate.colors.rgb}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)

