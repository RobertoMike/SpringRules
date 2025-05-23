package io.github.robertomike.springrules.validations.database

import io.github.robertomike.springrules.constraints.UniqueConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Checks if a value is unique in the database through the repository
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [UniqueConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Unique(
    /**
     * the repository class that will be used
     */
    val value: KClass<*>,
    /**
     * the repository method that will be used to search the value
     */
    val method: String = "findById",
    /**
     * the error message template
     */
    val message: String = "{spring-rules.database.unique}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated with the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)