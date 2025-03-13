package io.github.robertomike.springrules

import io.github.robertomike.springrules.config.SpringTestConfig
import jakarta.validation.ConstraintViolation
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SpringTestConfig::class)
abstract class BaseTest {
    private val templateRegex = "^\\{.*}\$".toRegex()

    fun checkMessages(constraints: Set<ConstraintViolation<*>>) {
        assert(constraints.isNotEmpty())
        constraints.forEach {
            val message = it.message
            assert(!message.matches(templateRegex))
        }
    }
}
