package com.lasoloz.tools.qpt.injections

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module

class InjectorUtil {
    companion object {
        private var injector: Injector? = null
        private var moduleChange = false

        private val modules = mutableListOf<Module>()
        private val lock = Object()

        fun getInjector(): Injector {
            synchronized(lock) {
                return if (moduleChange) {
                    createInjector()
                } else {
                    injector ?: createInjector()
                }
            }
        }

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
}
