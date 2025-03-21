package io.github.robertomike.springrules.responses

/**
 * This class represents a collection of violations by field and can have subfields.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ViolationsBySubFields(
    field: String,
    val messages: MutableList<String>? = null,
    var subfields: MutableList<ViolationsBySubFields>? = null
) : Violation(field) {
    constructor(field: String, path: MutableList<String>, message: String) : this(field) {
        if (path.isNotEmpty()) {
            startSubField()

            val subField = ViolationsBySubFields(path.removeFirst(), path, message)
            subfields?.add(subField)
            return
        }

        startMessages()
        messages!!.add(message)
    }

    /**
     * Adds a message to list of messages.
     *
     * @param messages The messages to add.
     */
    fun addMessage(vararg messages: String) {
        startMessages()
        this.messages!!.addAll(messages)
    }

    private fun startSubField() {
        subfields 
        if (subfields == null) {
             subfields = mutableListOf()
        }
    }

    private fun startMessages() {
        if (subfields == null) {
             subfields = mutableListOf()
        }
    }

    /**
     * Adds an element to list of subFields.
     *
     */
    fun addSubField(path: MutableList<String>, message: String) {
        if (path.isNotEmpty()) {
            startSubField()
            val pathField = path.removeFirst()
            subfields?.firstOrNull { it.field == pathField }?.addSubField(path, message) ?: subfields?.add(ViolationsBySubFields(pathField, path, message))
            return
        }

        addMessage(message)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ViolationsBySubFields

        if (messages != other.messages) return false
        if (subfields != other.subfields) return false

        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = messages.hashCode()
        result = 31 * result + subfields.hashCode() + super.hashCode()
        return result
    }
}