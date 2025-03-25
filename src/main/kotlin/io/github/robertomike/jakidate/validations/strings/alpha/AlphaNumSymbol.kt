package io.github.robertomike.jakidate.validations.strings.alpha

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import javax.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * The string must only contain symbols, numbers or alpha symbols
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
@Pattern(regexp = "^([.,;:?!'\"\\-—_/\\\\()\\[\\]{}@#&%*+=<>≠≈√Δ∑π¢$€£¥|~^¿¡ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÇØÆßa-zA-Z0-9]+)$", message = "{jakidate.strings.alpha.alpha-numeric-symbols}")
annotation class AlphaNumSymbol(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.alpha.alpha-numeric-symbols}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
