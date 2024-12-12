package io.github.robertomike.jakidate.validations.strings

import io.github.robertomike.jakidate.constraints.objects.strings.IsinConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

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
@Constraint(validatedBy = [IsinConstraint::class])
@Pattern(regexp = "^([A-Z]{2}-?[0-9A-Z]{9}-?\\d)$", message = "{jakidate.strings.isin}")
annotation class Isin(
    val message: String = "{jakidate.strings.isin}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)