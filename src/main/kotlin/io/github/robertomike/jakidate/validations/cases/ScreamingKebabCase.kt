package io.github.robertomike.jakidate.validations.cases

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
@Constraint(validatedBy = [])
@Pattern(regexp = "^(([A-Z0-9]+)([-])*)+\$", message = "{jakidate.case.screaming-kebab}")
annotation class ScreamingKebabCase(
    val message: String = "{jakidate.case.screaming-kebab}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)