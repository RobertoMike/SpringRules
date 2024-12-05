package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.ExistsValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.util.ClassUtils.getMethod
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.Optional

class ExistsConstraint : SimpleConstraint<ExistsValidation, Long> {
    @Autowired
    private lateinit var applicationContext: ApplicationContext
    private lateinit var method: String
    private lateinit var repository: Class<out Any>

    override fun initialize(constraintAnnotation: ExistsValidation) {
        this.method = constraintAnnotation.method
        this.repository = constraintAnnotation.repository.java
        super.initialize(constraintAnnotation)
    }

    override fun isValid(value: Long): Boolean {
        try {
            val instance = applicationContext.getBean(repository)
            val callable: Method = getMethod(repository, method, value.javaClass)
            val result = callable.invoke(instance, value)
            if (result is Optional<*>) {
                return result.isPresent
            }
            if (result is Boolean) {
                return result
            }

            return result != null
        } catch (e: IllegalAccessException) {
            return false
        } catch (e: NoSuchMethodException) {
            return false
        } catch (e: InvocationTargetException) {
            return false
        } catch (e: Exception) {
            return false
        }
    }
}