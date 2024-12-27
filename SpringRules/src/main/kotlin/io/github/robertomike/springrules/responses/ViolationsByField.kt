package io.github.robertomike.springrules.responses

class ViolationsByField(field: String, val messages: MutableList<String> = mutableListOf()): Violation(field) {
    constructor(field: String, message: String) : this(field, mutableListOf(message))

    fun add(vararg messages: String) {
        messages.forEach(this.messages::add)
    }
}