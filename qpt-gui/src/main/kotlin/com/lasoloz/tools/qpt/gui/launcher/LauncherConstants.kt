package com.lasoloz.tools.qpt.gui.launcher

/**
 * Launcher specific constants
 */
object LauncherConstants {
    /**
     * Injection names
     */
    object Injection {
        /**
         * Launcher window name key for injection
         */
        const val LAUNCHER_NAME_KEY = "Launcher"
    }

    /**
     * I18n keys
     */
    object I18n {
        /**
         * Title key for localization
         */
        const val LAUNCHER_TITLE_KEY = "launcher.title"
    }

    /**
     * Resource paths
     */
    object Paths {
        /**
         * Launcher FXML resource path
         */
        const val LAUNCHER_RESOURCE_PATH = "/fxml/launcher/stage/Launcher.fxml"

        /**
         * Launch bar FXML resource path
         */
        const val LAUNCH_BAR_RESOURCE_PATH = "/fxml/launcher/component/LaunchBarComponent.fxml"
        /**
         * Launch bar stylesheet path
         */
        const val LAUNCH_BAR_STYLESHEET_PATH = "/fxml/launcher/component/LaunchBarComponent.css"

        /**
         * Search result component resource path
         */
        const val SEARCH_RESULT_RESOURCE_PATH = "/fxml/launcher/component/SearchResultsComponent.fxml"
    }

    /**
     * Default width of the launcher
     */
    const val LAUNCHER_DEFAULT_WIDTH = 600.0
    /**
     * Default height of the launcher
     */
    const val LAUNCHER_DEFAULT_HEIGHT = 800.0
}
