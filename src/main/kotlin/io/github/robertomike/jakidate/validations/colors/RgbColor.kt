package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.constraints.RgbConstraint
import io.github.robertomike.jakidate.enums.RgbOptions
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [RgbConstraint::class])
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
annotation class RgbColor(
    val value: RgbOptions = RgbOptions.ALL,
    val message: String = "{jakidate.colors.rgb}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

