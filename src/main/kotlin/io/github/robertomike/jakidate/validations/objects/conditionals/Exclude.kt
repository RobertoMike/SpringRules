package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.conditionals.ExcludeConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * This annotation allow you to exclude fields based on another field of the object,
 * this is made possible using the ExcludeIf and ExcludeUnless annotation
 *
 * @see ExcludeIf
 * @see ExcludeUnless
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [ExcludeConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Exclude(
    /**
     * this will check if the excluded fields are empty
     */
    val checkEmpty: Boolean = true,
    /**
     * the error message template
     */
    val message: String = "{jakidate.objects.conditionals.exclude.default}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = [],
)