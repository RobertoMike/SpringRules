package io.github.robertomike.jakidate.validations.`object`

import io.github.robertomike.jakidate.constraints.objects.comparation.SameConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [SameConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Same(
    val message: String = "{spring-rules.same}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
