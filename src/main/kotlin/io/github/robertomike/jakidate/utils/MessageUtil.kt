package io.github.robertomike.jakidate.utils

import jakarta.validation.ConstraintValidatorContext
import java.lang.reflect.Field

class MessageUtil(private val context: ConstraintValidatorContext) {
    fun resetDefaultMessage() {
        context.disableDefaultConstraintViolation();
    }

    fun changeMessages(vararg messages: String) {
        resetDefaultMessage()
        messages.forEach(this::addMessage)
    }

    fun addMessage(message: String, vararg parameters: Pair<String, Any>) {
        val template = templateMessage(message)
        template.addConstraintViolation()
    }

    fun addMessageForProperty(property: String, message: String) {
        templateMessage(message)
            .addPropertyNode(property)
            .addConstraintViolation()
    }

    fun addMessageForProperty(property: Field, message: String) {
        templateMessage(message)
            .addPropertyNode(property.name)
            .addConstraintViolation()
    }

    // Todo: da provare dopo aver creato una validazione per array
    fun addMessageItemArray(property: String, key: Any, message: String) {
        templateMessage(message)
            .addPropertyNode(property)
            .addBeanNode()
                .inContainer(Map::class.java, 1)
                .inIterable()
                .atKey(key)
            .addConstraintViolation()
    }

    private fun templateMessage(message: String): ConstraintValidatorContext.ConstraintViolationBuilder {
        return context.buildConstraintViolationWithTemplate("{spring-rules.${message}}")
    }
}