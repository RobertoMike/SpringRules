package io.github.robertomike.jakidate.validations.it

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.YamlSource
import io.github.robertomike.jakidate.validations.documents.it.FiscalCode
import io.github.robertomike.jakidate.validations.documents.it.FiscalCode
import javax.validation.Validator
import org.junit.jupiter.params.ParameterizedTest

class FiscalCodeTest : BaseTest() {
    inner class Example(
        @field:FiscalCode
        val fiscalCode: String
    )

    @ParameterizedTest
    @YamlSource("/it/fiscalCode", "good")
    fun good(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @YamlSource("/it/fiscalCode", "wrong")
    fun wrong(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}