package io.github.robertomike.jakidate.validations.cases

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
@Pattern(regexp = "^(([A-Z]+)([a-z0-9]*))+\$", message = "{spring-rules.case.pascal}")
annotation class PascalCase