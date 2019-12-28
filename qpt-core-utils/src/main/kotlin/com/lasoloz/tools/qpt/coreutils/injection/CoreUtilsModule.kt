package com.lasoloz.tools.qpt.coreutils.injection

import com.google.inject.AbstractModule
import com.google.inject.Inject
import com.google.inject.Provides
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.coreutils.platform.ConfigFileResolver
import com.lasoloz.tools.qpt.coreutils.platform.GenericConfigFileResolver
import com.lasoloz.tools.qpt.coreutils.platform.OSType
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants

/**
 * Guice module for core utilities
 */
class CoreUtilsModule : AbstractModule() {
    /**
     * Provide config file resolver implementation
     *
     * @param relativeConfigDirectory Injected relative configuration directory
     */
    @Provides
    @Named(CoreConstants.Injection.CONFIG_FILE_RESOLVER_NAME_KEY)
    @Inject
    fun provideConfigFileResolver(
        @Named(CoreConstants.Injection.RELATIVE_CONFIG_DIRECTORY_NAME_KEY) relativeConfigDirectory: String
    ): ConfigFileResolver? = if (OSType.getType() == OSType.UNIX_LIKE) {
        GenericConfigFileResolver(relativeConfigDirectory)
    } else {
        null
    }
}
