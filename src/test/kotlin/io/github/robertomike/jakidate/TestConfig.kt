package io.github.robertomike.jakidate

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.extension.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.Volatile


class TestConfig : BeforeAllCallback, ParameterResolver {
    companion object {
        /**
         * volatile boolean to tell other threads, when unblocked, whether they should try attempt start-up.  Alternatively, could use AtomicBoolean.
         */
        @Volatile
        var started: Boolean = false
        lateinit var validator: Validator
        val LOCK: Lock = ReentrantLock()
        var NAMESPACE: ExtensionContext.Namespace = ExtensionContext.Namespace.GLOBAL
    }

    override fun beforeAll(context: ExtensionContext) {
        // lock the access so only one Thread has access to it
        LOCK.lock()
        try {
            if (!started) {
                started = true
            }

            validator = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .validator
        } catch (e: Exception) {
            println(e.message)
            throw RuntimeException(e)
        } finally {
            // free the access
            LOCK.unlock()
        }


        val globalStore = context.getStore(NAMESPACE)
        globalStore.put("Validator", validator)
    }

    private fun getSimpleName(parameterContext: ParameterContext): String {
        return parameterContext.parameter.type.simpleName
    }

    @Throws(ParameterResolutionException::class)
    override fun supportsParameter(parameterContext: ParameterContext, context: ExtensionContext): Boolean {
        return context.getStore(NAMESPACE)[getSimpleName(parameterContext)] != null
    }

    @Throws(ParameterResolutionException::class)
    override fun resolveParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext): Any {
        return extensionContext.getStore(NAMESPACE)[getSimpleName(parameterContext!!)]
    }
}