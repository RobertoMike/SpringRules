package io.github.robertomike.jakidate.validations.documents.it

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.YamlSource
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest

class FiscalCodeTest : BaseTest() {
    inner class Example(
        @field:FiscalCode
        val fiscalCode: String
    )

    @ParameterizedTest
    @YamlSource("/documents/it/fiscalCode", "good")
    fun good(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @YamlSource("/documents/it/fiscalCode", "wrong")
    fun wrong(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}