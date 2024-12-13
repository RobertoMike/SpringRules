package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.strings.ISIN
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ISINTest : BaseTest() {
    inner class Example(
        @field:ISIN
        val code: String
    )

    companion object {
        @JvmStatic
        fun validIsin(): Stream<String> {
            return loadAndGetYaml("/strings/isin")["good"]!!.stream()
        }
        @JvmStatic
        fun invalidIsin(): Stream<String> {
            return loadAndGetYaml("/strings/isin")["wrong"]!!.stream()
        }
    }

    @ParameterizedTest
    @MethodSource("validIsin")
    fun good(isin: String, validator: Validator) {
        val example = Example(isin)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("invalidIsin")
    fun wrong(isin: String, validator: Validator) {
        val example = Example(isin)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
    }
}
