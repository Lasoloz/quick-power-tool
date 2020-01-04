package com.lasoloz.tools.qpt.actions.injection

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.google.inject.multibindings.MapBinder
import com.google.inject.multibindings.ProvidesIntoSet
import com.google.inject.name.Named
import com.google.inject.name.Names
import com.lasoloz.tools.qpt.actions.basic.CommandActionDTO
import com.lasoloz.tools.qpt.actions.basic.CommandActionMapper
import com.lasoloz.tools.qpt.actions.config.mapper.ActionConfigMapper
import com.lasoloz.tools.qpt.actions.loader.ActionConfigLoader
import com.lasoloz.tools.qpt.actions.util.ActionConstants
import com.lasoloz.tools.qpt.actions.util.JsonMapperSetupUnit
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants
import com.lasoloz.tools.qpt.coreutils.util.SetupUnit

/**
 * Guice module for loading action-specific implementations
 */
class ActionsModule : AbstractModule() {
    override fun configure() {
        bind(ActionConfigLoader::class.java)
            .annotatedWith(Names.named(ActionConstants.Injection.ACTION_CONFIG_LOADER_NAME_KEY))
            .to(ActionConfigLoader::class.java)
            .`in`(Scopes.SINGLETON)

        MapBinder.newMapBinder(
            binder(),
            String::class.java,
            ActionConfigMapper::class.java,
            Names.named(ActionConstants.Injection.ACTION_CONFIG_DTO_MAPPER_MAP_NAME_KEY)
        )
            .addBinding(CommandActionDTO::class.java.name).toInstance(CommandActionMapper())
    }

    @ProvidesIntoSet
    fun provideJacksonSetup(@Named(CoreConstants.Injection.JSON_MAPPER_NAME_KEY) jsonMapper: ObjectMapper): SetupUnit {
        return JsonMapperSetupUnit(jsonMapper)
    }
}
