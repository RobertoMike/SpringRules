package io.github.robertomike.jakidate.exceptions

/**
 * Exception thrown when a violation of the rules occurs.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 * @param message the error message
 * @param cause the cause of the exception, if any
 * @constructor creates a new instance of the exception with the specified message and cause
 */
class RulesException(message: String, cause: Throwable? = null): RuntimeException(message, cause)