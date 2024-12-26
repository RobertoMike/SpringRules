package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.utils.getBeanAndExecute
import io.github.robertomike.springrules.utils.verifyIfPresent
import io.github.robertomike.springrules.validations.Unique
import org.springframework.context.ApplicationContext

class UniqueConstraint(private val applicationContext: ApplicationContext) : SimpleConstraint<Unique, Any>() {
    private val method: String
        get() = annotation.method
    private val repository: Class<out Any>
        get() = annotation.repository.java

    override fun isValid(value: Any): Boolean {
        return !applicationContext.getBeanAndExecute(repository, method, value)
            .verifyIfPresent()
    }
}