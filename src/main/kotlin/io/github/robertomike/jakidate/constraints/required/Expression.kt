package io.github.robertomike.jakidate.constraints.required

import io.github.robertomike.jakidate.constraints.AcceptedConstraint
import java.util.function.Function

val Expression: Function<Any, Boolean> = Function { value ->
    when (value) {
        is String -> value in AcceptedConstraint.acceptedStringValues
        is Number -> value.toInt() == 1
        else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported, create your own expression")
    }
}