package io.github.robertomike.jakidate.validations.booleans

import io.github.robertomike.jakidate.constraints.booleans.DeclinedConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotNull
import kotlin.reflect.KClass

/**
 * Annotation to validate that a field of type String, Number and Boolean.
 *
 * This annotation can be used to validate that a value is declined. It uses the [DeclinedConstraint] to perform the validation.
 *
 * The values for String are "no", "off", "false", "n", "0"
 *
 * @see DeclinedConstraint
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [DeclinedConstraint::class])
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
annotation class Declined(
    /**
     * the error message template
     */
    val message: String = "{jakidate.declined}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)

