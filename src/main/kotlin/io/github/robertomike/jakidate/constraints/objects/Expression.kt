package io.github.robertomike.jakidate.constraints.objects

import io.github.robertomike.jakidate.utils.acceptedStringValues
import io.github.robertomike.jakidate.utils.declinedStringValues

open class Expression {
    fun apply(value: Any, unless: Boolean): Boolean {
        return when (value) {
            is String -> value in (if (unless) declinedStringValues else acceptedStringValues)
            is Number -> value.toInt() == (if (unless) 0 else 1)
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported, create your own expression")
        }
    }
}