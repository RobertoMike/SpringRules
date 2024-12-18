package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.utils.hslRegex
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
@Pattern(regexp = hslRegex, message = "{jakidate.colors.hsl}")
annotation class HSLColor(
    val message: String = "{jakidate.colors.hsl}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

