package io.github.robertomike.jakidate.validations.`object`.required

import io.github.robertomike.jakidate.constraints.objects.required.RequiredConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [RequiredConstraint::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Required(
    val message: String = "{jakidate.required-if.default}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)