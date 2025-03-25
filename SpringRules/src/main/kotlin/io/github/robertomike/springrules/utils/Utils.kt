package io.github.robertomike.springrules.utils

import org.springframework.context.ApplicationContext
import org.springframework.util.ClassUtils.getMethodIfAvailable
import java.lang.reflect.Method
import java.util.*

/**
 * This method get a bean and execute a method on it using the ApplicationContext
 *
 * @see org.springframework.context.ApplicationContext
 */
fun ApplicationContext.getBeanAndExecute(repository: Class<out Any>, method: String, value: Any): Any? {
    val instance = this.getBean(repository)
    val callable: Method = getMethodIfAvailable(repository, method, value::class.java)
        ?: getMethod(repository, method)
    return callable.invoke(instance, value)
}

fun getMethod(clazz: Class<out Any>, method: String): Method {
    return clazz.methods.firstOrNull { it.name == method } ?: throw NoSuchMethodException("The $method was not found")
}

/**
 * Check if a value is present
 */
fun Any?.verifyIfPresent(): Boolean {
    if (this is Optional<*>) {
        return this.isPresent
    }
    if (this is Boolean) {
        return this
    }

    return this != null
}