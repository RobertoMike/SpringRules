package io.github.robertomike.jakidate.validations.string.alpha

import jakarta.validation.constraints.Pattern

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
@Pattern(regexp = "[a-zA-Z\\d]+", message = "{spring-rules.alpha.numeric}")
annotation class AlphaNum