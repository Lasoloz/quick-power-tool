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
         * Injection name for setup unit set
         */
        const val SETUP_UNIT_NAME_KEY = "SetupUnits"

        /**
         * Injection name for relative configuration directory
         */
        const val RELATIVE_CONFIG_DIRECTORY_NAME_KEY = "RelativeConfigDirectory"

        /**
         * Injection name for JSON mapper (Jackson)
         */
        const val JSON_MAPPER_NAME_KEY = "JacksonJSONMapper"
    }


    /**
     * Default configuration directory for quick power tool
     */
    const val DEFAULT_QPT_CONFIG_DIRECTORY = ".quick-power-tool"
}
