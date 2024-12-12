package io.github.robertomike.jakidate.validations.strings.alpha

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
@Pattern(regexp = "^([\\.,;:?!'\\\"\\-\\—_\\/\\\\()\\[\\]{}@#&%\\*\\+=<>≠≈√Δ∑π¢\\\$€£¥\\|~\\^¿¡ÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÇØÆßa-zA-Z0-9]+)\$", message = "{jakidate.alpha.symbols}")
annotation class AlphaSymbols(
    val message: String = "{jakidate.alpha.symbols}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
