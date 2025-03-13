package io.github.robertomike.jakidate.validations.strings.cases

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * The string must be snake case
 *
 * An example is: accept_language, hello_world
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
@Pattern(regexp = "^(([a-z0-9]+)([_])*)+\$", message = "{jakidate.string.case.snake}")
annotation class SnakeCase(
    /**
     * the error message template
     */
    val message: String = "{jakidate.string.case.snake}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)