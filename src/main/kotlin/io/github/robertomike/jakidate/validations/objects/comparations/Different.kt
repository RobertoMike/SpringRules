package io.github.robertomike.jakidate.validations.objects.comparations

import io.github.robertomike.jakidate.constraints.objects.comparation.DifferentConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * This annotation allow you to check if two or more fields of the object are different,
 * this is made possible using the DifferentAs annotation
 *
 * @see DifferentAs
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [DifferentConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Different(
    /**
     * the error message template
     */
    val message: String = "{jakidate.objects.comparations.different}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = [],
)
