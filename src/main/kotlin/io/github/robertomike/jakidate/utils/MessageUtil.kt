package io.github.robertomike.jakidate.utils

import javax.validation.ConstraintValidatorContext
import java.lang.reflect.Field
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions

/**
 * Utility class for working with constraint validation messages.
 *
 * This class provides methods for building and customizing constraint validation messages.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class MessageUtil(private val context: ConstraintValidatorContext) {
    /**
     * The base template for constraint validation messages.
     */
    var baseTemplate = "jakidate"
    /**
     * The function used to add message parameters to the constraint validation context.
     */
    private var addParameterFunction: KFunction<*>? = null

    /**
     * Resets the default constraint validation message.
     *
     * This method disables the default constraint validation message.
     */
    fun resetDefaultMessage() {
        context.disableDefaultConstraintViolation()
    }

    /**
     * Changes the constraint validation messages to the specified messages.
     *
     * This method resets the default constraint validation message and adds the specified messages.
     *
     * @param messages the messages to be added
     */
    fun changeMessages(vararg messages: String) {
        resetDefaultMessage()
        messages.forEach(this::addMessage)
    }

    /**
     * Adds a constraint validation message with the specified parameters.
     *
     * This method adds a constraint validation message with the specified parameters to the context.
     *
     * @param message the message to be added
     * @param parameters the parameters for the message
     */
    fun addMessage(message: String, vararg parameters: Pair<String, Any>) {
        addParameters(*parameters)
        val template = templateMessage(message)
        template.addConstraintViolation()
    }

    /**
     * Adds parameters to the constraint validation context.
     *
     * This method adds the specified parameters to the constraint validation context.
     *
     * @param parameters the parameters to be added
     */
    fun addParameters(vararg parameters: Pair<String, Any>) {
        val function = addParameterFunction ?: context::class.declaredFunctions
            .firstOrNull { it.name == "addMessageParameter" }

        function?.let {
            parameters.forEach {
                function.call(context, it.first, it.second)
            }
        }
    }

    /**
     * Adds a constraint validation message for the specified property with the specified parameters.
     *
     * This method adds a constraint validation message for the specified property with the specified parameters to the context.
     *
     * @param property the property for which the message is being added
     * @param message the message to be added
     * @param parameters the parameters for the message
     */
    fun addMessageForProperty(property: String, message: String, vararg parameters: Pair<String, Any>) {
        addParameters(*parameters)
        templateMessage(message)
            .addPropertyNode(property)
            .addConstraintViolation()
    }

    /**
     * Adds a constraint validation message for the specified property with the specified parameters.
     *
     * This method adds a constraint validation message for the specified property with the specified parameters to the context.
     *
     * @param property the property for which the message is being added
     * @param message the message to be added
     * @param parameters the parameters for the message
     */
    fun addMessageForProperty(property: Field, message: String, vararg parameters: Pair<String, Any>) {
        addMessageForProperty(property.name, message, *parameters)
    }

    /**
     * Builds a constraint validation message template with the specified message.
     *
     * This method builds a constraint validation message template with the specified message and returns a ConstraintViolationBuilder.
     *
     * @param message the message for which the template is being built
     * @return a ConstraintViolationBuilder
     */
    private fun templateMessage(message: String): ConstraintValidatorContext.ConstraintViolationBuilder {
        return context.buildConstraintViolationWithTemplate("{${baseTemplate}.${message}}")
    }
}