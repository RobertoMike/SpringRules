package io.github.robertomike.jakidate.examples

import io.github.robertomike.jakidate.validations.StartsWith

data class User(
    @StartsWith("Jake")
    val name: String,
)
