package io.github.robertomike.springrules.config

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorFactory
import org.springframework.context.ApplicationContext


class CustomConstraintValidatorFactory(private val context: ApplicationContext) : ConstraintValidatorFactory {
    override fun <T : ConstraintValidator<*, *>> getInstance(param: Class<T>): T {
        val constructor = param.declaredConstructors.first()

        val params = constructor.parameterTypes
            .map { if (it == ApplicationContext::class.java) context else context.getBean(it) }
            .toTypedArray()

        return constructor.newInstance(*params) as T
    }

    override fun releaseInstance(param: ConstraintValidator<*, *>) {
    }
}
