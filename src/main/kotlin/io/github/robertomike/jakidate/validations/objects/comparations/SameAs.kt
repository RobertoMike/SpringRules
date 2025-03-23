package io.github.robertomike.jakidate.validations.objects.comparations

/**
 * This annotation is put it on top of two or more fields to check if they are the same
 *
 * You need to put Same annotation on top of the class
 *
 * @see Same
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
annotation class SameAs(
    /**
     * This field is used to separate logic between different conditions
     */
    val value: String = "default",
)
