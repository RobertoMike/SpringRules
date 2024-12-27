package io.github.robertomike.springrules.configs

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = ["io.github.robertomike.springrules"]
)
open class RegistrationConfig