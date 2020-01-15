package com.lasoloz.tools.qpt.injections

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module

/**
 * Injector utility for creating a single injector instance for the whole application in a thread-safe manner
 */
object InjectorUtil {
    private var injector: Injector? = null
    private var moduleChange = false

    private val modules = mutableListOf<Module>()
    private val lock = Object()

    /**
     * Get injector instance for the application (thread-safe)
     *
     * @return Injector singleton instance
     */
    fun getInjector(): Injector {
        synchronized(lock) {
            return if (moduleChange) {
                createInjector()
            } else {
                injector ?: createInjector()
            }
        }
    }

    /**
     * Register a Guice module and mark injector for recreation (thread-safe)
     *
     * @param module Guice module to register
     */
    fun registerModule(module: Module) {
        synchronized(lock) {
            modules.add(module)
            moduleChange = true
        }
    }

    private fun createInjector(): Injector {
        return Guice.createInjector(modules).also {
            injector = it
            moduleChange = false
        }
    }
}
