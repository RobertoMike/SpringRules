package io.github.robertomike.jakidate.validations.strings.alpha

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import jakarta.validation.constraints.Pattern
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
