package io.github.robertomike.springrules

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringRulesApplication

fun main(args: Array<String>) {
    runApplication<SpringRulesApplication>(*args)
}
