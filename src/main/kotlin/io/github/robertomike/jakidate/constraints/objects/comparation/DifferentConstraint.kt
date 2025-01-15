package io.github.robertomike.jakidate.constraints.objects.comparation

import io.github.robertomike.jakidate.validations.objects.Different
import io.github.robertomike.jakidate.validations.objects.DifferentAs
import java.lang.reflect.Field

/**
 * A constraint validator that checks if two fields are different.
 *
 * This class extends the [CompareFieldsConstraint] and provides a specific implementation
 * for the [Different] annotation.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class DifferentConstraint(override val message: String = "different") : CompareFieldsConstraint<Different>() {
    /**
     * The annotation field that is used to retrieve the value for grouping fields.
     */
    override val annotationField = DifferentAs::class.java
    /**
     * Whether the logic of the constraint should be inverted.
     *
     * In this case, the logic is inverted, meaning that the constraint will pass if the fields are different.
     */
    override val invertedLogic
        get() = true

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