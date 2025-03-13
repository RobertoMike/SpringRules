package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.YamlSource
import javax.validation.Validator
import org.junit.jupiter.params.ParameterizedTest

abstract class YamlTest : BaseTest() {
    abstract fun getExample(value: String): Any

    @ParameterizedTest
    @YamlSource(element = "good")
    fun good(isin: String, validator: Validator) {
        val constraints = validator.validate(getExample(isin))

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @YamlSource(element = "wrong")
    fun wrong(isin: String, validator: Validator) {
        val constraints = validator.validate(getExample(isin))

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}
