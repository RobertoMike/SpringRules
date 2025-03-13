package io.github.robertomike.jakidate.validations.strings.cases

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * The string must be train case
 *
 * An example is: Accept-Language, Hello-World
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
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
@Constraint(validatedBy = [])
@ReportAsSingleViolation
@Pattern(regexp = "^(([A-Z])([a-z0-9]*))(-([A-Z]+)([a-z0-9]*))*$", message = "{jakidate.strings.case.train}")
annotation class TrainCase(
    /**
     * the error message template
     */
    val message: String = "{jakidate.string.case.train}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)