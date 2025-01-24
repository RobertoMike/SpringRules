package io.github.robertomike.springrules.responses

/**
 * A violation that contains a single message.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class SingleViolation(field: String, val message: String): Violation(field)