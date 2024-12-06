package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.ExistsValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.util.ClassUtils.getMethod
import java.lang.reflect.Method
import java.util.Optional

class ExistsConstraint: SimpleConstraint<ExistsValidation, Long>() {
    @Autowired
    private lateinit var applicationContext: ApplicationContext
    private val method: String
        get() = annotation.method
    private val repository: Class<out Any>
        get() = annotation.repository.java

    override fun isValid(value: Long): Boolean {
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
    }
}