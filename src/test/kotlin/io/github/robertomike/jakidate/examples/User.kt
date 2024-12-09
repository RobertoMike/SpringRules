package io.github.robertomike.jakidate.examples

import io.github.robertomike.jakidate.validations.StartsWith

data class User(
    @field:StartsWith("Jake")
    val name: String,
)
