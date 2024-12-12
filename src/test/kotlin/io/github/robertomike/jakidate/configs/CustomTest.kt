package io.github.robertomike.jakidate.configs

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.platform.commons.annotation.Testable

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Testable
@ExtendWith(TestConfig::class)
annotation class CustomTest
