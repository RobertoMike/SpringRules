package io.github.robertomike.jakidate.utils

import java.lang.reflect.Field


fun <T : Annotation> Any.getFieldsByAnnotation(annotation: Class<T>): Sequence<Field> {
    return this.javaClass.declaredFields
        .asSequence()
        .filter { it.isAnnotationPresent(annotation) }
}
fun <T> Annotation.getFieldValue(name: String): T {
    return this.javaClass.declaredMethods
        .first { it.name == name }
        .invoke(this) as T
}

fun Field.getValue(original: Any): Any? {
    this.trySetAccessible()

    return this.get(original)
}

fun Map<String, List<Field>>.validate(lambda: (list: List<Field>) -> Boolean): Boolean {
    var valid = true
    this.forEach { (_, list) ->
        if (!lambda(list))
            valid = false
    }

    return valid
}