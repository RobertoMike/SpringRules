package io.github.robertomike.springrules.responses

/**
 * Base class to represent a Violation
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
abstract class Violation(val field: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Violation) return false

        if (field != other.field) return false

        return true
    }

    override fun hashCode(): Int {
        return field.hashCode()
    }
}