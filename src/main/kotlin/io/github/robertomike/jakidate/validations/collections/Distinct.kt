package io.github.robertomike.jakidate.validations.collections

import io.github.robertomike.jakidate.constraints.collections.distincts.DistinctArrayConstraint
import io.github.robertomike.jakidate.constraints.collections.distincts.DistinctCollectionConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating that a collection has not duplicate elements.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [DistinctCollectionConstraint::class, DistinctArrayConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Distinct(
    /**
     * the error message template
     */
    val message: String = "{jakidate.collections.distinct}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = [],
)
