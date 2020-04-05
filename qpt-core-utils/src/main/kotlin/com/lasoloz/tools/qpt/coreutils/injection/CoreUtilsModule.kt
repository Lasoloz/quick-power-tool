package com.lasoloz.tools.qpt.coreutils.injection

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.inject.AbstractModule
import com.google.inject.Inject
import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.multibindings.Multibinder
import com.google.inject.name.Named
import com.google.inject.name.Names
import com.lasoloz.tools.qpt.coreutils.platform.ConfigFileResolver
import com.lasoloz.tools.qpt.coreutils.platform.GenericConfigFileResolver
import com.lasoloz.tools.qpt.coreutils.platform.OSType
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants
import com.lasoloz.tools.qpt.coreutils.util.SetupUnit

/**
 * Guice module for core utilities
 */
class CoreUtilsModule : AbstractModule() {
    override fun configure() {
        bind(String::class.java)
            .annotatedWith(Names.named(CoreConstants.Injection.RELATIVE_CONFIG_DIRECTORY_NAME_KEY))
            .toInstance(CoreConstants.DEFAULT_QPT_CONFIG_DIRECTORY)

        Multibinder.newSetBinder(
            binder(),
            SetupUnit::class.java,
            Names.named(CoreConstants.Injection.SETUP_UNIT_NAME_KEY)
        )
    }

    /**
     * Provide config file resolver implementation
     *
     * @param relativeConfigDirectory Injected relative configuration directory
     * @return Resolver
     */
    @Provides
    @Inject
    fun provideConfigFileResolver(
        @Named(CoreConstants.Injection.RELATIVE_CONFIG_DIRECTORY_NAME_KEY) relativeConfigDirectory: String
    ): ConfigFileResolver? = if (OSType.getType() == OSType.UNIX_LIKE) {
        GenericConfigFileResolver(relativeConfigDirectory)
    } else {
        null
    }

    /**
     * Provide Jackson JSON mapper with Kotlin module registered
     *
     * @return JSON mapper
     */
    @Provides
    @Named(CoreConstants.Injection.JSON_MAPPER_NAME_KEY)
    @Singleton
    fun provideJsonMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()
}
