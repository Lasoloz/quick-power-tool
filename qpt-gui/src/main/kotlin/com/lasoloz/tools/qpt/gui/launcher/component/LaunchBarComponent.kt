package com.lasoloz.tools.qpt.gui.launcher.component

import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.Paths.LAUNCH_BAR_RESOURCE_PATH
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.Paths.LAUNCH_BAR_STYLESHEET_PATH
import com.lasoloz.tools.qpt.gui.util.AbstractGuiComponent

/**
 * Launch bar component for searching launch configurations and opening settings or quitting the launch window
 */
class LaunchBarComponent : AbstractGuiComponent(LAUNCH_BAR_RESOURCE_PATH) {
    init {
        this.stylesheets.add(LAUNCH_BAR_STYLESHEET_PATH)
    }
}
