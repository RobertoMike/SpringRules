package io.github.robertomike.jakidate.validations.string.alpha

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
@Pattern(regexp = "[a-zA-Z\\d]+", message = "{jakidate.alpha.numeric}")
annotation class AlphaNum(
    val message: String = "{jakidate.alpha.numeric}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)