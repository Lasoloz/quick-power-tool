package com.lasoloz.tools.qpt.actions.util

/**
 * Action module specific constants
 */
object ActionConstants {
    /**
     * Injection names for actions module
     */
    object Injection {
        /**
         * Injection name for action configuration loader
         */
        const val ACTION_CONFIG_LOADER_NAME_KEY = "ActionConfigLoader"

        /**
         * Injection name for map of action configuration dto mappers
         */
        const val ACTION_CONFIG_DTO_MAPPER_MAP_NAME_KEY = "ActionConfigMappers"
    }

    /**
     * Default configuration file path relative to the application configuration
     */
    const val ACTION_CONFIG_FILE_PATH = "action-configurations.json"
}
