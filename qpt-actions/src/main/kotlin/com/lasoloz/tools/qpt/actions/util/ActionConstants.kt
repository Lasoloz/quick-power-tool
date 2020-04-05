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
         * Injection name for map of action configuration models to their mappers
         */
        const val ACTION_CONFIG_MODEL_TO_MAPPER_NAME_KEY = "ActionConfigModelsToMappers"

        /**
         * Injection name for map of type ids to their models
         */
        const val ACTION_CONFIG_TYPE_ID_TO_MODEL_NAME_KEY = "ActionConfigTypeIdsToModels"
    }

    /**
     * Command action configuration type id for jackson databind
     */
    const val COMMAND_ACTION_CONFIG_TYPE_ID = "command"

    /**
     * Default configuration file path relative to the application configuration
     */
    const val ACTION_CONFIG_FILE_PATH = "action-configurations.json"
}
