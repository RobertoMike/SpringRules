package io.github.robertomike.springrules.responses

data class Violations(val violations: MutableList<Violation> = ArrayList()) {
    fun addError(field: String, message: String, useSingleViolation: Boolean) {
        if (useSingleViolation) {
            violations.add(SingleViolation(field, message))
            return
        }

        val violation = violations.find { it.field == field } as ViolationsByField?

        violation?.add(message) ?: {
            violations.add(ViolationsByField(field, message))
        }
    }
}
