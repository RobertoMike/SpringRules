package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Between

class BetweenConstraint: SimpleConstraint<Between, Any>() {

    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Int, is Long -> value in annotation.min.toLong()..annotation.max.toLong()
            is Double -> value in annotation.min.toDouble()..annotation.max.toDouble()
            is Float -> value in annotation.min.toFloat()..annotation.max.toFloat()
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}