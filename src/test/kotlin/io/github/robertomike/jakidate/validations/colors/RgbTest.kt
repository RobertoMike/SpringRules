package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.enums.RgbOptions
import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.utils.YamlSource
import io.github.robertomike.jakidate.validations.YamlTest
import javax.validation.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest

@YamlFileSource("/colors/rgb")
class RgbTest : YamlTest() {
    inner class Example(
        @field:RgbColor
        val value: String
    )
    inner class Legacy(
        @field:RgbColor(RgbOptions.LEGACY)
        val value: String
    )
    inner class New(
        @field:RgbColor(RgbOptions.NEW)
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }

    @Test
    fun goodLegacy(validator: Validator) {
        val constraints = validator.validate(Legacy("rgb(255,255,255)"))

        assert(constraints.isEmpty())
    }

    @Test
    fun goodNew(validator: Validator) {
        val constraints = validator.validate(New("rgb(255 255 255)"))

        assert(constraints.isEmpty())
    }
}
