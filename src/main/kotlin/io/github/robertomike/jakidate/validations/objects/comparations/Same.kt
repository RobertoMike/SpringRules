package io.github.robertomike.jakidate.validations.objects.comparations

import io.github.robertomike.jakidate.constraints.objects.comparation.SameConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * This annotation allows you to check if two or more fields of the object are the same,
 * this is made possible using the SameAs annotation
 *
 * @see SameAs
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [SameConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Same(
    /**
     * the error message template
     */
    val message: String = "{jakidate.objects.comparations.same}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated with the constraint
     */
    val payload: Array<KClass<out Payload>> = [],
)
