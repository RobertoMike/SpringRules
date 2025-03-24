package io.github.robertomike.jakidate

import io.github.robertomike.jakidate.configs.CustomTest
import javax.validation.ConstraintViolation

@CustomTest
abstract class BaseTest {
    private val templateRegex = "^\\{.*}$".toRegex()

    fun checkMessages(constraints: Set<ConstraintViolation<*>>) {
        constraints.forEach {
            val message = it.message
            assert(!message.matches(templateRegex))
        }
    }
}
