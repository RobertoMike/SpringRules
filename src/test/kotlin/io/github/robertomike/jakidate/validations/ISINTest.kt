package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.YamlSource
import io.github.robertomike.jakidate.validations.strings.ISIN
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest

class ISINTest : BaseTest() {
    inner class Example(
        @field:ISIN
        val code: String
    )

    @ParameterizedTest
    @YamlSource("/strings/isin", "good")
    fun good(isin: String, validator: Validator) {
        val example = Example(isin)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @YamlSource("/strings/isin", "wrong")
    fun wrong(isin: String, validator: Validator) {
        val example = Example(isin)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}
