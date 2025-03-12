package io.github.robertomike.springrules.config

import io.github.robertomike.jakidate.configs.TestConfig
import jakarta.validation.Configuration
import org.junit.jupiter.api.extension.*
import org.mockito.Mockito
import org.springframework.context.ApplicationContext

class SpringTestConfig : TestConfig(), BeforeAllCallback, ParameterResolver {
    private val context = Mockito.mock(ApplicationContext::class.java)

    override fun customizeValidator(factory: Configuration<*>): Configuration<*> {
        return factory.constraintValidatorFactory(CustomConstraintValidatorFactory(context))
    }

     override fun registerParameters(store: ExtensionContext.Store) {
         store.put("ApplicationContext", context)
     }
}