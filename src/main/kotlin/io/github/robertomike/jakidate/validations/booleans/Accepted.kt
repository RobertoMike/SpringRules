package io.github.robertomike.jakidate.validations.booleans

import io.github.robertomike.jakidate.constraints.booleans.AcceptedConstraint
import io.github.robertomike.jakidate.constraints.booleans.DeclinedConstraint
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.NotNull
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
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
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

