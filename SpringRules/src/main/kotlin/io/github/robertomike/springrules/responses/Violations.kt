package io.github.robertomike.springrules.responses

import io.github.robertomike.springrules.configs.ViolationType

/**
 * This class represents a collection of validation errors.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
data class Violations(val violations: MutableList<Violation> = mutableListOf()) {
    fun addError(fieldPath: MutableList<String>, message: String, violationType: ViolationType) {
        when (violationType) {
            ViolationType.SINGLE_MESSAGE -> violations.add(SingleViolation(fieldPath.joinToString("."), message))
            ViolationType.MULTIPLE_MESSAGE -> {
                val field = fieldPath.joinToString(".")

                val violation = violations.find { it.field == field } as ViolationsByField?

                violation?.add(message)
                    ?: violations.add(ViolationsByField(field, message))
            }
            ViolationType.SUBFIELDS_MESSAGES -> {
                val field = fieldPath.removeFirst()
                val violation = violations.find { it.field == field } as ViolationsBySubFields?

                violation?.addSubField(fieldPath, message)
                    ?: violations.add(ViolationsBySubFields(field, fieldPath, message))
            }
        }
    }
}
