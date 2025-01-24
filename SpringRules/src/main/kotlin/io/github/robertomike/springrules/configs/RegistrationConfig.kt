package io.github.robertomike.springrules.configs

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Configuration class for SpringRules registration.
 *
 * This class is responsible for configuring the component scan for SpringRules.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@Configuration
@ComponentScan(
    basePackages = ["io.github.robertomike.springrules"]
)
open class RegistrationConfig