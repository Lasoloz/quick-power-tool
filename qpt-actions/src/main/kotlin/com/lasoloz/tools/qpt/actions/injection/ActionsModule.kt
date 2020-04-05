package com.lasoloz.tools.qpt.actions.injection

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.google.inject.TypeLiteral
import com.google.inject.multibindings.MapBinder
import com.google.inject.name.Names
import com.lasoloz.tools.qpt.actions.basic.CommandActionMapper
import com.lasoloz.tools.qpt.actions.basic.CommandActionModel
import com.lasoloz.tools.qpt.actions.config.mapper.ActionConfigMapper
import com.lasoloz.tools.qpt.actions.config.model.AbstractActionConfigModel
import com.lasoloz.tools.qpt.actions.loader.ActionConfigList
import com.lasoloz.tools.qpt.actions.loader.ActionConfigLoader
import com.lasoloz.tools.qpt.actions.util.ActionConstants
import kotlin.reflect.KClass

/**
 * Guice module for loading action-specific implementations
 */
class ActionsModule : AbstractModule() {
    override fun configure() {
        bind(ActionConfigLoader::class.java).`in`(Scopes.SINGLETON)
        bind(ActionConfigList::class.java).`in`(Scopes.SINGLETON)

        bindModelsToMappers()
        bindTypeIdsToModels()
    }

    private fun bindModelsToMappers() {
        MapBinder.newMapBinder(
            binder(),
            String::class.java,
            ActionConfigMapper::class.java,
            Names.named(ActionConstants.Injection.ACTION_CONFIG_MODEL_TO_MAPPER_NAME_KEY)
        )
            .addBinding(CommandActionModel::class.java.name).toInstance(CommandActionMapper())
    }

    private fun bindTypeIdsToModels() {
        MapBinder.newMapBinder(
            binder(),
            object : TypeLiteral<String>() {},
            object : TypeLiteral<KClass<out AbstractActionConfigModel>>() {},
            Names.named(ActionConstants.Injection.ACTION_CONFIG_TYPE_ID_TO_MODEL_NAME_KEY)
        )
            .addBinding(ActionConstants.COMMAND_ACTION_CONFIG_TYPE_ID).toInstance(CommandActionModel::class)
    }
}
