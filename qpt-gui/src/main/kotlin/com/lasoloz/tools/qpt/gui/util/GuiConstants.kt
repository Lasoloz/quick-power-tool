package com.lasoloz.tools.qpt.gui.util

/**
 * Gui specific constants
 */
object GuiConstants {
    /**
     * Injection names
     */
    object Injection {
        /**
         * Resource bundle key for the gui module
         */
        const val GUI_RESOURCE_NAME_KEY = "GuiResourceBundle"

        /**
         * Injection name for action config categorizer set
         */
        const val ACTION_CATEGORIZERS_NAME_KEY = "ActionCategorizers"
    }

    /**
     * Resource bundle path for i18n messages
     */
    const val MESSAGES_RESOURCE_BUNDLE_PATH = "i18n/messages"

    /**
     * Path to the default stylesheet
     */
    const val DEFAULT_STYLESHEET_PATH = "fxml/styles.css"
}
