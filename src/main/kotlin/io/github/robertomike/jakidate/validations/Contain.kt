package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.constraints.ContainConstraint
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating that the validated value is contained in the list of value.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [ContainConstraint::class])
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
annotation class Contain(
    /**
     * the array of values that will be searched
     */
    val value: Array<String>,
    /**
     * The operator that will be used to validate the value
     */
    val operator: Operator = Operator.OR,
    /**
     * the error message template
     */
    val message: String = "{jakidate.contain}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
) {
    enum class Operator {
        OR, AND
    }
}