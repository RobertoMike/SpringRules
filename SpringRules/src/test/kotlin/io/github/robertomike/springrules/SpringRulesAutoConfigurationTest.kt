package io.github.robertomike.springrules

import io.github.robertomike.springrules.advice.ConstraintViolationAdvice
import io.github.robertomike.springrules.advice.MethodArgumentNotValidExceptionAdvice
import io.github.robertomike.springrules.configs.RegistrationConfig
import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.configs.ViolationType
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Boots a real Spring context through the same [org.springframework.boot.autoconfigure.AutoConfiguration.imports]
 * entry points Spring Boot itself uses, instead of the mocked [io.github.robertomike.springrules.config.SpringTestConfig]
 * used by the rest of the suite. This is what actually proves the library's autoconfiguration graph still wires up
 * correctly under the upgraded Spring Boot / Spring Framework versions.
 */
class SpringRulesAutoConfigurationTest {
    private val contextRunner = ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(
                PropertyPlaceholderAutoConfiguration::class.java,
                ConfigurationPropertiesAutoConfiguration::class.java,
                SpringRulesConfig::class.java,
                RegistrationConfig::class.java
            )
        )

    @Test
    fun `loads the full auto-configuration graph with default properties`() {
        contextRunner.run { context ->
            assertTrue(context.isRunning)

            val config = context.getBean(SpringRulesConfig::class.java)
            assertEquals(ViolationType.SUBFIELDS_MESSAGES, config.violationBody)
            assertTrue(config.controllerAdvice.constraintViolations)
            assertTrue(config.controllerAdvice.methodArgumentNotValid)

            assertNotNull(context.getBean(ConstraintViolationAdvice::class.java))
            assertNotNull(context.getBean(MethodArgumentNotValidExceptionAdvice::class.java))
        }
    }

    @Test
    fun `binds kebab-case properties with relaxed binding`() {
        contextRunner
            .withPropertyValues(
                "spring-rules.violation-body=SINGLE_MESSAGE",
                "spring-rules.controller-advice.constraint-violations=false"
            )
            .run { context ->
                val config = context.getBean(SpringRulesConfig::class.java)
                assertEquals(ViolationType.SINGLE_MESSAGE, config.violationBody)
                assertFalse(config.controllerAdvice.constraintViolations)
            }
    }

    @Test
    fun `disabling the constraint-violations property removes only that advice bean`() {
        contextRunner
            .withPropertyValues("spring-rules.controller-advice.constraint-violations=false")
            .run { context ->
                assertFalse(context.containsBean("constraintViolationAdvice"))
                assertNotNull(context.getBean(MethodArgumentNotValidExceptionAdvice::class.java))
            }
    }

    @Test
    fun `disabling the method-argument-not-valid property removes only that advice bean`() {
        contextRunner
            .withPropertyValues("spring-rules.controller-advice.method-argument-not-valid=false")
            .run { context ->
                assertFalse(context.containsBean("methodArgumentNotValidExceptionAdvice"))
                assertNotNull(context.getBean(ConstraintViolationAdvice::class.java))
            }
    }

    @Test
    fun `an invalid enum value for violation-body fails context startup`() {
        contextRunner
            .withPropertyValues("spring-rules.violation-body=NOT_A_REAL_TYPE")
            .run { context ->
                assertTrue(context.startupFailure != null)
            }
    }
}
