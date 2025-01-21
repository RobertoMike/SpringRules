package io.github.robertomike.jakidate.constraints.distincts

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.Distinct

/**
 * A constraint that checks if a collection is distinct.
 *
 * @author Giorgio Andrei
 * @since 1.0.0
 */
class DistinctArrayConstraint : SimpleConstraint<Distinct, Array<Any>>() {
    /**
     * Checks if the collection is distinct.
     *
     * @param value The collection to be validated.
     * `true` if the collection is distinct, `false` otherwise.
     */
    override fun isValid(value: Array<Any>): Boolean {
        if (value.isEmpty())
            return true

        return value.distinct().size == value.size
    }
}