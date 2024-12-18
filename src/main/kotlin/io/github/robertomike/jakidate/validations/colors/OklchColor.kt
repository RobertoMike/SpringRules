package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.utils.oklchRegex
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [])
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
@Pattern(regexp = oklchRegex, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{jakidate.colors.oklch}")
annotation class OklchColor(
    val message: String = "{jakidate.colors.oklch}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

