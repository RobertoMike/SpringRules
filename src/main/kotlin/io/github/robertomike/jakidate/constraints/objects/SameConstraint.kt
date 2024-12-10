package io.github.robertomike.jakidate.constraints.objects

import io.github.robertomike.jakidate.validations.`object`.Same
import io.github.robertomike.jakidate.validations.`object`.SameField
import java.lang.reflect.Field

class SameConstraint(override val message: String = "same") : CompareFieldsConstraint<Same>() {
    override val annotationField = SameField::class.java

    override fun groupBy(fields: Sequence<Field>): Map<String, List<Field>> {
        return fields.groupBy { it.getAnnotation(annotationField).value }
    }
}