package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.UniqueValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.util.ClassUtils.getMethod
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.Optional

class UniqueConstraint : SimpleConstraint<UniqueValidation, String> {
    @Autowired
    private lateinit var applicationContext: ApplicationContext
    private lateinit var method: String
    private lateinit var repository: Class<out Any>

    override fun initialize(constraintAnnotation: UniqueValidation) {
        this.method = constraintAnnotation.method
        this.repository = constraintAnnotation.repository.java
        super.initialize(constraintAnnotation)
    }

    override fun isValid(value: String): Boolean {
        try {
            val instance = applicationContext.getBean(repository)
            val callable: Method = getMethod(repository, method, value.javaClass)
            val result = callable.invoke(instance, value)
            if (result is Optional<*>) {
                return (result).isEmpty
            }
            if (result is Boolean) {
                return !result
            }
            return result == null
        } catch (e: IllegalAccessException) {
            return false
        } catch (e: NoSuchMethodException) {
            return false
        } catch (e: InvocationTargetException) {
            return false
        }
    }
}