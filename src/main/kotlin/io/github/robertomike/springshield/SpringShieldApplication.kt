package io.github.robertomike.springshield

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringShieldApplication

fun main(args: Array<String>) {
    runApplication<SpringShieldApplication>(*args)
}
