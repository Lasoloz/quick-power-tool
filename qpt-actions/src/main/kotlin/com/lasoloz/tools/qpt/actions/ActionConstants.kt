package com.lasoloz.tools.qpt.actions

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
    }

    /**
     * Default configuration file path relative to the application configuration
     */
    const val ACTION_CONFIG_FILE_PATH = "action-configurations.json"
}
