package io.github.robertomike.springrules.configs

import jakarta.validation.constraints.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.validation.annotation.Validated

@ConfigurationProperties(prefix = "spring-rules")
@PropertySource("classpath:spring-rules.properties")
@Validated
class SpringRulesConfig {
    @field:NotNull
    val useSingleViolation: Boolean = false
}