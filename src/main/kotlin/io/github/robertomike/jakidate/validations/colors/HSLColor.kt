package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.utils.hslRegex
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * Validates a string is an HSL color
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Pattern(regexp = hslRegex, message = "{jakidate.colors.hsl}")
annotation class HSLColor(
    /**
     * the error message template
     */
    val message: String = "{jakidate.colors.hsl}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)

