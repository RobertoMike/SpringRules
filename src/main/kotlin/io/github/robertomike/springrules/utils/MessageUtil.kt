package io.github.robertomike.springrules.utils

import jakarta.validation.ConstraintValidatorContext

class MessageUtil(private val context: ConstraintValidatorContext) {
    fun resetDefaultMessage() {
        context.disableDefaultConstraintViolation();
    }

    fun changeMessages(vararg messages: String) {
        resetDefaultMessage()
        messages.forEach {
            templateMessage(it).addConstraintViolation()
        }
    }

    fun addMessageForProperty(property: String, message: String) {
        templateMessage(message)
            .addPropertyNode(property)
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