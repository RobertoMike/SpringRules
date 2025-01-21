package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.AcceptedConstraint
import io.github.robertomike.jakidate.constraints.DeclinedConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotNull
import kotlin.reflect.KClass

/**
 * Annotation to validate that a field of type String, Number and Boolean.
 *
 * This annotation can be used to validate that a value is accepted. It uses the [AcceptedConstraint] to perform the validation.
 *
 * The values for String are "yes", "on", "true", "y", "1"
 *
 * @see DeclinedConstraint
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [AcceptedConstraint::class])
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
@NotNull
annotation class Accepted(
    /**
     * the error message template
     */
    val message: String = "{jakidate.accepted}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)

