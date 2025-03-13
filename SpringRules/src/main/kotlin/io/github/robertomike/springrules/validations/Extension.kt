package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.ExtensionConstraint
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating file extensions.
 *
 * This annotation is used to validate the extension of a file based on a list of allowed extensions.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [ExtensionConstraint::class])
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
annotation class Extension(
    /**
     * the allowed extensions
     */
    val value: Array<String> = [],
    /**
     * the error message template
     */
    val message: String = "{spring-rules.file.extension}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)