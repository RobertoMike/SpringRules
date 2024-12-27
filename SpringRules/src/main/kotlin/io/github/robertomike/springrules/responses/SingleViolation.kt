package io.github.robertomike.springrules.responses

class SingleViolation(field: String, val message: String): Violation(field)