package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.validations.ExistsValidation
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.util.ClassUtils.getMethod
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.Optional

class ExistsConstraint : ConstraintValidator<ExistsValidation, Long?> {
    @Autowired
    private val applicationContext: ApplicationContext? = null
    var method: String? = null
    var repository: Class<out Any>? = null

    override fun initialize(constraintAnnotation: ExistsValidation) {
        this.method = constraintAnnotation.method
        this.repository = constraintAnnotation.repository.java
        super.initialize(constraintAnnotation)
    }

    override fun isValid(value: Long?, constraintValidatorContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }
        try {
            val instance = applicationContext!!.getBean(repository)
            val callable: Method = getMethod(repository, method, value.javaClass)
            val result = callable.invoke(instance, value)
            if (result is Optional<*>) {
                return result.isPresent
            }
            if (result is Boolean) {
                return result
            }
            if (result == null) {
                return false
            }
        } catch (e: IllegalAccessException) {
            return false
        } catch (e: NoSuchMethodException) {
            return false
        } catch (e: InvocationTargetException) {
            return false
        }
        return true
    }
}