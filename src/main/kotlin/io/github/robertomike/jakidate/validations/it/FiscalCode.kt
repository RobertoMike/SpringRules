package io.github.robertomike.jakidate.validations.it

import io.github.robertomike.jakidate.constraints.it.FiscalCodeConstraint
import jakarta.validation.Constraint
import jakarta.validation.OverridesAttribute
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Pattern.Flag
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [FiscalCodeConstraint::class])
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
@ReportAsSingleViolation
@Pattern(regexp = "^[A-Z]{6}\\d{2}[ABCDEHLMPRST]\\d{2}[A-Z]\\d{3}[A-Z]\$", message = "{jakidate.it.fiscal-code}")
annotation class FiscalCode(
    @get:OverridesAttribute(constraint = Pattern::class, name = "flags")
    val flags: Array<Flag> = [Flag.CASE_INSENSITIVE],
    val message: String = "{jakidate.it.fiscal-code}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
