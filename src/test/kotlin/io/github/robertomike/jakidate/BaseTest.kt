package io.github.robertomike.jakidate

import io.github.robertomike.jakidate.configs.CustomTest
import jakarta.validation.ConstraintViolation
import org.slf4j.LoggerFactory

@CustomTest
abstract class BaseTest {
    private val logger = LoggerFactory.getLogger(BaseTest::class.java)
    private val templateRegex = "^\\{.*}$".toRegex()

    fun checkMessages(constraints: Set<ConstraintViolation<*>>) {
        constraints.forEach {
            val message = it.message
            logger.info("Message: $message")
            assert(!message.matches(templateRegex))
        }
    }
}
