package io.github.robertomike.jakidate.validations.strings.cases

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * The string must be pascal case
 *
 * An example is: AcceptLanguage, HelloWorld
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Constraint(validatedBy = [])
@ReportAsSingleViolation
@Pattern(regexp = "^(([A-Z]+)([a-z0-9]*))+\$", message = "{jakidate.strings.cases.pascal-case}")
annotation class PascalCase(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.cases.pascal-case}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)