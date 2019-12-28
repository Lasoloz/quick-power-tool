package com.lasoloz.tools.qpt.actions.loader

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.ActionConstants
import com.lasoloz.tools.qpt.coreutils.platform.ConfigFileResolver
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants

class ActionConfigLoader @Inject constructor(
    @Named(CoreConstants.Injection.CONFIG_FILE_RESOLVER_NAME_KEY) private val configFileResolver: ConfigFileResolver
) {
    fun loadActionConfigs(): List<ActionConfig> {
        configFileResolver.resolve(ActionConstants.ACTION_CONFIG_FILE_PATH)
        return listOf()
    }
}
