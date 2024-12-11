package io.github.robertomike.jakidate.constraints.objects.comparation

import io.github.robertomike.jakidate.validations.`object`.Different
import io.github.robertomike.jakidate.validations.`object`.DifferentField
import java.lang.reflect.Field

class DifferentConstraint(override val message: String = "different") : CompareFieldsConstraint<Different>() {
    override val annotationField = DifferentField::class.java
    override val invertedLogic
        get() = true

    override fun groupBy(fields: Sequence<Field>): Map<String, List<Field>> {
        return fields.groupBy { it.getAnnotation(annotationField).value }
    }
}