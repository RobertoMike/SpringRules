package io.github.robertomike.jakidate.validations

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
@Repeatable
@Pattern(regexp = "^/([a-zA-Z0-9-_]+/)*[a-zA-Z0-9-_]+\$", message = "{spring-rules.path}")
annotation class Path