package io.github.robertomike.springrules.configs

import javax.validation.Valid
import javax.validation.constraints.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.validation.annotation.Validated

/**
 * Configuration properties for Spring Rules.
 *
 * This class holds the configuration properties for Spring Rules, such as whether to use a single violation or not.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "spring-rules")
@PropertySource("classpath:spring-rules.properties")
@Validated
class SpringRulesConfig {
    /**
     * Whether to use a single violation or not. Defaults to `false`.
     */
    @field:NotNull
    var violationBody = ViolationType.SUBFIELDS_MESSAGES
    /**
     * The configuration for controller advice. This property is validated using the [ControllerAdvice] class.
     */
    @field:NotNull
    @field:Valid
    var controllerAdvice: ControllerAdvice = ControllerAdvice()
}

/**
 * Configuration class for controller advice settings.
 *
 * This class holds the configuration properties for controller advice in Spring Rules.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ControllerAdvice {
    /**
     * Flag to enable or disable controller advice for constraint violations.
     *
     * If set to `true`, controller advice will be enabled for constraint violations.
     * Otherwise, it will be disabled.
     *
     * Default value is `true`.
     */
    @field:NotNull
    var constraintViolations: Boolean = true
    /**
     * Flag to enable or disable controller advice for method argument not valid exceptions.
     *
     * If set to `true`, controller advice will be enabled for method argument not valid exceptions.
     * Otherwise, it will be disabled.
     *
     * Default value is `true`.
     */
    @field:NotNull
    var methodArgumentNotValid: Boolean = true
}