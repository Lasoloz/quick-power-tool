package com.lasoloz.tools.qpt.actions.loader

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.config.dto.AbstractActionConfigDTO
import com.lasoloz.tools.qpt.actions.config.mapper.ActionConfigMapper
import com.lasoloz.tools.qpt.actions.util.ActionConstants
import com.lasoloz.tools.qpt.actions.util.ActionLoadException
import com.lasoloz.tools.qpt.coreutils.platform.ConfigFileResolver
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants

/**
 * Action configuration loader
 *
 * By using the injected services the action configurations are loaded and then processed by an JSON object mapper
 *
 * @param configFileResolver Configuration file resolver
 * @param mapper JSON object mapper
 */
@JvmSuppressWildcards
class ActionConfigLoader @Inject constructor(
    @Named(CoreConstants.Injection.CONFIG_FILE_RESOLVER_NAME_KEY) private val configFileResolver: ConfigFileResolver,
    @Named(CoreConstants.Injection.JSON_MAPPER_NAME_KEY) private val mapper: ObjectMapper,
    @Named(ActionConstants.Injection.ACTION_CONFIG_DTO_MAPPER_MAP_NAME_KEY) private val dtoMappers
    : Map<String, ActionConfigMapper>
) {
    /**
     * Load the list of action configurations defined in the configuration file
     *
     * @return Action configurations
     */
    fun loadActionConfigs(): List<ActionConfig> = configFileResolver
        .resolve(ActionConstants.ACTION_CONFIG_FILE_PATH)
        .let { file ->
            mapper.runCatching {
                readValue<List<AbstractActionConfigDTO>>(file)
            }.getOrElse { exception ->
                throw ActionLoadException("Read error for file \"${file.path}\":\n${exception.message}")
            }.map { configDTO ->
                dtoMappers[configDTO::class.java.name]?.mapToAction(configDTO)
                    ?: throw ActionLoadException("Couldn't find mapper for one of the configuration DTOs")
            }
        }
}
