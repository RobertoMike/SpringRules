package io.github.robertomike.springrules.utils

import org.springframework.context.ApplicationContext
import org.springframework.util.ClassUtils.getMethod
import java.lang.reflect.Method
import java.util.*

fun ApplicationContext.getBeanAndExecute(repository: Class<out Any>, method: String, value: Any): Any? {
    val instance = this.getBean(repository)
    val callable: Method = getMethod(repository, method, value.javaClass)
    return callable.invoke(instance, value)
}

fun Any?.verifyIfPresent(): Boolean {
    if (this is Optional<*>) {
        return this.isPresent
    }
    if (this is Boolean) {
        return this
    }

    return this != null
}