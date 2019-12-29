package com.lasoloz.tools.qpt.actions.injection

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.lasoloz.tools.qpt.actions.loader.ActionConfigLoader

/**
 * Guice module for loading action-specific implementations
 */
class ActionsModule : AbstractModule() {
    override fun configure() {
        // TODO: Find a way to implement with named Singleton using annotatedWith instead of provider method
        bind(ActionConfigLoader::class.java)
            .`in`(Scopes.SINGLETON)
    }
}
