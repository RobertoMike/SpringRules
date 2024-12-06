package io.github.robertomike.springrules.utils

import jakarta.validation.ConstraintValidatorContext

class MessageUtil(val context: ConstraintValidatorContext) {
    fun resetDefaultMessage() {
        context.disableDefaultConstraintViolation();
    }

    fun changeMessages(vararg messages: String) {
        resetDefaultMessage()
        messages.forEach {
            context.buildConstraintViolationWithTemplate("{spring-rules.${it}}")
                .addConstraintViolation()
        }
    }
}