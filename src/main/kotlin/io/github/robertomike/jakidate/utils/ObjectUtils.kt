package io.github.robertomike.jakidate.utils

import java.lang.reflect.Field

/**
 * Gets the fields of an object that are annotated with the specified annotation.
 *
 * This method returns a sequence of fields that are annotated with the specified annotation.
 *
 * @param annotation the annotation to look for
 * @return a sequence of fields
 */
fun <T : Annotation> Any.getFieldsByAnnotation(annotation: Class<T>): Sequence<Field> {
    tailrec fun getFields(currentClass: Class<*>?, fields: MutableList<Field> = mutableListOf()): List<Field> {
        return when {
            currentClass == null || currentClass == Any::class.java -> fields
            else -> {
                fields.addAll(currentClass.declaredFields)
                getFields(currentClass.superclass, fields)
            }
        }
    }


    return getFields(this.javaClass)
        .asSequence()
        .filter { it.isAnnotationPresent(annotation) }
}

/**
 * Gets the value of a field from an annotation.
 *
 * This method returns the value of the specified field from the annotation.
 *
 * @param name the name of the field to get
 * @return the value of the field
 */
fun <T> Annotation.getFieldValue(name: String): T {
    return this.javaClass.declaredMethods
        .first { it.name == name }
        .invoke(this) as T
}

/**
 * Gets the value of a field from an object.
 *
 * This method returns the value of the specified field from the object.
 *
 * @param original the object to get the field value from
 * @return the value of the field
 */
fun Field.getValue(original: Any): Any? {
    this.isAccessible = true

    return this.get(original)
}

/**
 * Validates a map of fields.
 *
 * This method validates a map of fields using the specified lambda function.
 *
 * @param lambda the lambda function to use for validation
 * @return true if the map is valid, false otherwise
 */
fun Map<String, List<Field>>.validate(lambda: (list: List<Field>) -> Boolean): Boolean {
    var valid = true
    this.forEach { (_, list) ->
        if (!lambda(list))
            valid = false
    }

    return valid
}