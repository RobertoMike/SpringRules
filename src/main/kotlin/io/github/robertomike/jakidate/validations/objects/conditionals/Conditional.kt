package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.Expression
import kotlin.reflect.KClass

/**
 * This annotation is put it on top of two or more fields, there is a condition field
 * and n° quantity of fields that will be excluded when the condition field is true
 *
 * You need to put Exclude annotation on top of the class
 *
 * @see Exclude
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Conditional(
    /**
     * This field is used to separate logic between different conditions
     */
    val key: String = "default",
    /**
     * This field is used to define the expression that will be used to evaluate the condition,
     * when this is different from supported expressions or behavior
     */
    val expression: KClass<out Expression<*>> = Expression::class,
)