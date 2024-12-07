package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.validations.Unique
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.util.ClassUtils.getMethod
import java.lang.reflect.Method
import java.util.Optional

class UniqueConstraint: SimpleConstraint<Unique, String>() {
    @Autowired
    private lateinit var applicationContext: ApplicationContext
    private val method: String
        get() = annotation.method
    private val repository: Class<out Any>
        get() = annotation.repository.java

    override fun isValid(value: String): Boolean {
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
    }
}