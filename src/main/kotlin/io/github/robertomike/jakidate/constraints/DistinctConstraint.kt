package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Distinct

class DistinctConstraint: SimpleConstraint<Distinct, Array<Any>>() {

    override fun isValid(value: Array<Any>): Boolean {
        if (value.isEmpty()) return false
        return when (value[0]) {
            is Int -> (value as Array<Int>).distinct().size != value.size
            is String -> (value as Array<String>).distinct().size != value.size
            else -> throw IllegalArgumentException("Array must contain integers or strings")
        }
    }
}