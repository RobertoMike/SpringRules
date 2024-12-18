package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.YamlSource
import io.github.robertomike.jakidate.validations.strings.ISSN
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest

class ISSNTest : BaseTest() {
    inner class Example(
        @field:ISSN
        val code: String
    )

    @ParameterizedTest
    @YamlSource("/strings/issn", "good")
    fun good(issn: String, validator: Validator) {
        val example = Example(issn)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @YamlSource("/strings/issn", "wrong")
    fun wrong(issn: String, validator: Validator) {
        val example = Example(issn)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
    }
}
