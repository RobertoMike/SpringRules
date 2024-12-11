package io.github.robertomike.jakidate.validations.objects

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
annotation class SameAs(
    /**
     * This field is used to separate logic between different conditions
     */
    val value: String = "default",
)
