package io.github.robertomike.jakidate.validations.strings

import io.github.robertomike.jakidate.constraints.objects.strings.ISSNConstraint
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
@Constraint(validatedBy = [ISSNConstraint::class])
@Pattern(regexp = "^(\\d{4}-\\d{3}[Xx0-9])$", message = "{jakidate.strings.isin}")
annotation class ISSN(
    val message: String = "{jakidate.strings.isin}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)