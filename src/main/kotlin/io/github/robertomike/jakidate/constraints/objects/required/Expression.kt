package io.github.robertomike.jakidate.constraints.objects.required

import io.github.robertomike.jakidate.utils.acceptedStringValues
import java.util.function.Function

interface Expression: Function<Any, Boolean> {
    override fun apply(value: Any): Boolean {
        return when (value) {
            is String -> value in acceptedStringValues
            is Number -> value.toInt() == 1
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported, create your own expression")
        }
    }
}