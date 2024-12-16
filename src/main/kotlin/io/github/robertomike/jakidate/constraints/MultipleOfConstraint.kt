package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.MultipleOf
import java.math.BigDecimal
import java.math.BigInteger

class MultipleOfConstraint : SimpleConstraint<MultipleOf, Any>() {

    override fun isValid(value: Any): Boolean {
        return when (value) {
            is Int -> value % annotation.value.toInt() == 0
            is BigInteger -> value % annotation.value.toBigInteger() == BigInteger.ZERO
            is BigDecimal -> value % annotation.value.toBigDecimal() == BigDecimal.ZERO
            is Long -> value % annotation.value.toLong() == 0L
            is Double -> value % annotation.value.toDouble() == 0.0
            is Float -> value % annotation.value.toFloat() == 0.0f
            else -> throw UnsupportedOperationException("Type ${value::class.simpleName} is not supported")
        }
    }
}