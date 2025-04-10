package io.github.robertomike.jakidate.constraints.objects.comparation

import io.github.robertomike.jakidate.validations.objects.comparations.Same
import io.github.robertomike.jakidate.validations.objects.comparations.SameAs
import java.lang.reflect.Field

/**
 * A constraint validator that checks if two fields are the same.
 *
 * This class extends the [CompareFields] and provides a specific implementation
 * for the [Same] annotation.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class SameConstraint(override val message: String = "objects.comparations.same") : CompareFields<Same>() {
    /**
     * The annotation field that is used to retrieve the value for grouping fields.
     */
    override val annotationField = SameAs::class.java

    /**
     * Groups the fields by the value of the [annotationField] annotation.
     *
     * This method is used to group fields that should be compared together.
     *
     * @param fields the fields to group
     * @return a map of field groups, where each key is the value of the [annotationField] annotation
     * and the value is a list of fields that have that annotation value
     */
    override fun groupBy(fields: Sequence<Field>): Map<String, List<Field>> {
        return fields.groupBy { it.getAnnotation(annotationField).value }
    }
}