package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.Contain

class ContainConstraint : SimpleConstraint<Contain, Any>() {
    override fun isValid(value: Any): Boolean {
        return when (value) {
            is String -> annotation.value
                .any { value.equals(it, ignoreCase = true) }

            is Int, is Long -> annotation.value
                .any { value.toString() == it }

            is Double -> annotation.value
                .any { value.toString() == it }

            is Float -> annotation.value
                .any { value.toString() == it }

            is Map<*, *> -> annotation.value
                .any { value.containsKey(it) }

            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}