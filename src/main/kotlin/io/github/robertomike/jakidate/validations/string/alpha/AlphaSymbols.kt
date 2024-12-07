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
@Repeatable
@Pattern(regexp = "^([\\.,;:?!'\\\"\\-\\—_\\/\\\\()\\[\\]{}@#&%\\*\\+=<>≠≈√Δ∑π¢\\\$€£¥\\|~\\^¿¡ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÇØÆßa-zA-Z0-9]+)\$", message = "{spring-rules.alpha.symbols}")
annotation class AlphaSymbols
