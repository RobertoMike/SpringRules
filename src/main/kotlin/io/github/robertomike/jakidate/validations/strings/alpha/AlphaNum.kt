package io.github.robertomike.jakidate.validations.strings.alpha

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * The string must contain only alphanumeric symbols
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
@Pattern(regexp = "[a-zA-Z\\d]+", message = "{jakidate.string.alpha.numeric}")
annotation class AlphaNum(
    /**
     * the error message template
     */
    val message: String = "{jakidate.string.alpha.numeric}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)