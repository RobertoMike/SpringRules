package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.utils.getBeanAndExecute
import io.github.robertomike.springrules.utils.verifyIfPresent
import io.github.robertomike.springrules.validations.database.Exists
import io.github.robertomike.springrules.validations.database.Unique
import org.springframework.context.ApplicationContext

/**
 * A constraint that checks if a value exists in a Spring application context.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ExistsConstraint(private val applicationContext: ApplicationContext) : SimpleConstraint<Exists, Any>() {
    /**
     * The method to be executed on the repository to check for uniqueness.
     *
     * This method is specified in the [Unique] annotation.
     */
    private val method: String
        get() = annotation.method

    /**
     * The repository class that contains the method to be executed.
     *
     * This repository is specified in the [Unique] annotation.
     */
    private val repository: Class<out Any>
        get() = annotation.value.java

    /**
     * Checks if the given value exists in the Spring application context.
     *
     * @param value the value to be checked
     * @return true if the value exists, false otherwise
     */
    override fun isValid(value: Any): Boolean {
        return applicationContext.getBeanAndExecute(repository, method, value)
            .verifyIfPresent()
    }
}