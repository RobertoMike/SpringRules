package io.github.robertomike.jakidate.validations.objects.comparations

/**
 * This annotation is put it on top of two or more fields to check if they are different
 *
 * You need to put Different annotation on top of the class
 *
 * @see Different
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
annotation class DifferentAs(
    /**
     * This field is used to separate logic between different conditions
     */
    val value: String = "default",
)
