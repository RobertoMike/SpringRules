package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Distinct

class DistinctConstraint: SimpleConstraint<Distinct, Array<Any>>() {
    override fun isValid(value: Array<Any>): Boolean {
        if (value.isEmpty())
            return true

        return value.distinct().size != value.size
    }
}