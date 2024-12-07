package io.github.robertomike.jakidate.validations.`object`.required

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiredIf(
    val message: String = "{spring-rules.required-if.default}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)