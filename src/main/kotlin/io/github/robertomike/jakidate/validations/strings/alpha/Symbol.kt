package io.github.robertomike.jakidate.validations.strings.alpha

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * The string must only contain symbols.
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
@Pattern(regexp = "^([.,;:?!'\"\\-—_/\\\\()\\[\\]{}@#&%*+=<>≠≈√Δ∑π¢$€£¥|~^¿¡ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÇØÆß]+)$", message = "{jakidate.strings.alpha.symbols}")
annotation class Symbol(
    /**
     * the error message template
     */
    val message: String = "{jakidate.strings.alpha.symbols}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
