package io.github.robertomike.jakidate.utils

import jakarta.validation.ConstraintValidatorContext
import java.lang.reflect.Field
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions

class MessageUtil(private val context: ConstraintValidatorContext) {
    var baseTemplate = "jakidate"
    private var addParameterFunction: KFunction<*>? = null

    fun resetDefaultMessage() {
        context.disableDefaultConstraintViolation()
    }

    fun changeMessages(vararg messages: String) {
        resetDefaultMessage()
        messages.forEach(this::addMessage)
    }

    fun addMessage(message: String, vararg parameters: Pair<String, Any>) {
        addParameters(*parameters)
        val template = templateMessage(message)
        template.addConstraintViolation()
    }

    fun addParameters(vararg parameters: Pair<String, Any>) {
        val function = addParameterFunction ?: context::class.declaredFunctions
            .firstOrNull { it.name == "addMessageParameter" }

        function?.let {
            parameters.forEach {
                function.call(context, it.first, it.second)
            }
        }
    }

    fun addMessageForProperty(property: String, message: String, vararg parameters: Pair<String, Any>) {
        addParameters(*parameters)
        templateMessage(message)
            .addPropertyNode(property)
            .addConstraintViolation()
    }

    fun addMessageForProperty(property: Field, message: String, vararg parameters: Pair<String, Any>) {
        addParameters(*parameters)
        templateMessage(message)
            .addPropertyNode(property.name)
            .addConstraintViolation()
    }

    // Todo: da provare dopo aver creato una validazione per array
    fun addMessageItemArray(property: String, key: Any, message: String, vararg parameters: Pair<String, Any>) {
        addParameters(*parameters)
        templateMessage(message)
            .addPropertyNode(property)
            .addBeanNode()
            .inContainer(Map::class.java, 1)
            .inIterable()
            .atKey(key)
            .addConstraintViolation()
    }

    private fun templateMessage(message: String): ConstraintValidatorContext.ConstraintViolationBuilder {
        return context.buildConstraintViolationWithTemplate("{${baseTemplate}.${message}}")
    }
}