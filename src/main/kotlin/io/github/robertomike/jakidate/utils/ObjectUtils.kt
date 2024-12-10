package io.github.robertomike.jakidate.utils

import java.lang.reflect.Field


fun <T : Annotation> Any.getFieldsByAnnotation(annotation: Class<T>): Sequence<Field> {
    return this.javaClass.declaredFields
        .asSequence()
        .filter { it.isAnnotationPresent(annotation) }
}

fun Field.getValue(original: Any): Any? {
    this.trySetAccessible()

    return this.get(original)
}