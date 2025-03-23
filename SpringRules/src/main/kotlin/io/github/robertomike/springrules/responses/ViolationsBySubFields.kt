package io.github.robertomike.springrules.responses

/**
 * This class represents a collection of violations by field and can have subfields.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ViolationsBySubFields(
    field: String,
    var messages: MutableList<String>? = null,
    var subfields: MutableList<ViolationsBySubFields>? = null
) : Violation(field) {
    constructor(field: String, path: MutableList<String>, message: String) : this(field) {
        if (path.isNotEmpty()) {
            startSubField()

            val subField = ViolationsBySubFields(path.removeFirst(), path, message)
            subfields?.add(subField)
            return
        }

        addMessage(message)
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
        if (messages == null) {
             messages = mutableListOf()
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
        if (other !is ViolationsBySubFields) return false
        if (!super.equals(other)) return false

        if (messages != other.messages) return false
        if (subfields != other.subfields) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (messages?.hashCode() ?: 0)
        result = 31 * result + (subfields?.hashCode() ?: 0)
        return result
    }


}