package io.github.robertomike.springrules.responses

/**
 * This class represents a collection of violations by field.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ViolationsByField(field: String, val messages: MutableList<String> = mutableListOf()): Violation(field) {
    constructor(field: String, message: String) : this(field, mutableListOf(message))

    /**
     * Adds a list of messages to the violations by field.
     *
     * @param messages The messages to add.
     */
    fun add(vararg messages: String) {
        messages.forEach(this.messages::add)
    }
}