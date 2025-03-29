package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.conditionals.RequiredConstraint
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * This annotation allow you to check if a value is required based on another field of the object,
 * this is made possible using the RequiredIf and RequiredUnless annotation
 *
 * @see RequiredIf
 * @see RequiredUnless
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [RequiredConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Required(
    /**
     * this will check if the required fields are empty
     */
    val checkEmpty: Boolean = true,
    /**
     * the error message template
     */
    val message: String = "{jakidate.objects.conditionals.required.default}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)