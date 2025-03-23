package io.github.robertomike.springrules.validations.file

import io.github.robertomike.springrules.constraints.ExtensionConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
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