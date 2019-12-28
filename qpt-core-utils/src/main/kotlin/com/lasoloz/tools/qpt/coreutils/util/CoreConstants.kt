package com.lasoloz.tools.qpt.coreutils.util

/**
 * Constants of `core-utils` module
 */
object CoreConstants {
    /**
     * Injection names
     */
    object Injection {
        /**
         * Injection name for [com.lasoloz.tools.qpt.coreutils.platform.ConfigFileResolver]
         */
        const val CONFIG_FILE_RESOLVER_NAME_KEY = "configFileResolver"

        /**
         * Injection name for relative configuration directory
         */
        const val RELATIVE_CONFIG_DIRECTORY_NAME_KEY = "relativeConfigDirectory"
    }


    /**
     * Default configuration directory for quick power tool
     */
    const val DEFAULT_QPT_CONFIG_DIRECTORY = ".quick-power-tool"
}