package com.lasoloz.tools.qpt.coreutils.injection

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants

/**
 * Bind defaults to the core utils
 */
class CoreUtilsDefaultModule : AbstractModule() {
    override fun configure() {
        bind(String::class.java)
            .annotatedWith(Names.named(CoreConstants.Injection.RELATIVE_CONFIG_DIRECTORY_NAME_KEY))
            .toInstance(CoreConstants.DEFAULT_QPT_CONFIG_DIRECTORY)
    }
}
