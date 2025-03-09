package io.github.robertomike.jakidate.examples

import io.github.robertomike.jakidate.validations.strings.start.StartsWith

data class User(
    @field:StartsWith("Jake")
    val name: String,
)
