package io.github.robertomike.jakidate.validations.objects.required

import io.github.robertomike.jakidate.constraints.objects.required.Expression
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
annotation class RequiredIf(
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
    val conditional: Boolean = false,
    /**
     * This field is used to separate logic between different conditions
     */
    val key: String = "default",
    /**
     * This field is used to define the expression that will be used to evaluate the condition,
     * when this is different from supported expressions or behavior
     */
    val expression: KClass<out Expression> = Expression::class,
)
