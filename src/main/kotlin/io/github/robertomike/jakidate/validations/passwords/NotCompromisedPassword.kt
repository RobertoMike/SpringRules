package io.github.robertomike.jakidate.validations.passwords

import io.github.robertomike.jakidate.constraints.passwords.NotCompromisedPasswordConstraint
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating a password that has not been compromised.
 *
 * This annotation can be used to validate a password and ensure it has not been compromised in a data breach.
 *
 * The website used to check is [https://haveibeenpwned.com/](https://haveibeenpwned.com/)
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [NotCompromisedPasswordConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class NotCompromisedPassword(
    /**
     * the error message template
     */
    val message: String = "{jakidate.password.compromised}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
