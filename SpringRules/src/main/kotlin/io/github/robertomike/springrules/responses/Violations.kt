package io.github.robertomike.springrules.responses

/**
 * This class represents a collection of validation errors.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
data class Violations(val violations: MutableList<Violation> = mutableListOf()) {
    fun addError(field: String, message: String, useSingleViolation: Boolean) {
        if (useSingleViolation) {
            violations.add(SingleViolation(field, message))
            return
        }

        val violation = violations.find { it.field == field } as ViolationsByField?

        violation?.add(message) ?: violations.add(ViolationsByField(field, message))
    }
}
