package io.github.robertomike.springrules.utils

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.util.ClassUtils.getMethodIfAvailable
import org.springframework.util.StringUtils
import java.lang.reflect.Method
import java.util.*

fun ApplicationContext.getBeanByClassOrName(clazz: Class<out Any>): Any {
    try {
        return this.getBean(clazz)
    } catch (_: Exception) {
    }

    try {
        return this.getBean(StringUtils.uncapitalize(clazz.simpleName))
    } catch (_: Exception) {
    }

    val name = when {
        clazz.isAnnotationPresent(Repository::class.java) -> clazz.getAnnotation(Repository::class.java)?.value
        clazz.isAnnotationPresent(Service::class.java) -> clazz.getAnnotation(Service::class.java)?.value
        clazz.isAnnotationPresent(Component::class.java) -> clazz.getAnnotation(Component::class.java)?.value
        else -> throw RuntimeException("The bean $clazz was not found")
    }

    name?.let {
        try {
            return this.getBean(name)
        } catch (_: Exception) {
        }

    }

    throw RuntimeException("The bean $clazz was not found")
}

/**
 * This method gets a bean and executes a method on it using the ApplicationContext
 *
 * @see org.springframework.context.ApplicationContext
 */
fun ApplicationContext.getBeanAndExecute(repository: Class<out Any>, method: String, value: Any): Any? {
    val instance = this.getBeanByClassOrName(repository)
    val callable = getMethodIfAvailable(instance::class.java, method, value::class.java)
        ?: getMethod(instance::class.java, method)
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