package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.Expression
import kotlin.reflect.KClass

/**
 * This annotation is put it on top of two or more fields, there is a condition field
 * and n° quantity of fields that will be excluded when the condition field is false
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
annotation class ExcludeUnless(
    /**
     * ConditionalField
     * This field is used to define what are the conditions fields and necessary fields
     *
     * If the value is true, the field will be considered as condition field.
     *
     * If true, the value cannot be null
     *
     * If the value is false, the field will be considered as necessary field
     */
    val condition: Boolean = false,
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