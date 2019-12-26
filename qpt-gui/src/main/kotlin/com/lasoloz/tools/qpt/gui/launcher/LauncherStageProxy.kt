package com.lasoloz.tools.qpt.gui.launcher

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.LAUNCHER_DEFAULT_HEIGHT
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.LAUNCHER_DEFAULT_WIDTH
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.LAUNCHER_RESOURCE_PATH
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.LAUNCHER_TITLE_KEY
import com.lasoloz.tools.qpt.gui.stage.StageProxy
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.gui.util.Constants.DEFAULT_STYLESHEET_PATH
import com.lasoloz.tools.qpt.gui.util.Constants.GUI_RESOURCE_BUNDLE_NAME
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.util.*

/**
 * Stage proxy implementation for the launcher stage
 *
 * @param resourceBundle Resource bundle for translations
 * @param launcherState Launcher state for holding launcher specific options
 */
@JvmSuppressWildcards
class LauncherStageProxy
@Inject constructor(
    @Named(GUI_RESOURCE_BUNDLE_NAME) private val resourceBundle: ResourceBundle,
    private val launcherState: LauncherState
) : StageProxy {
    private lateinit var launcher: Stage

    override fun initStage() {
        FXMLLoader.load<Parent>(javaClass.getResource(LAUNCHER_RESOURCE_PATH), resourceBundle).let { root ->
            launcher = Stage(StageStyle.UNDECORATED)
            launcher.scene = Scene(root, LAUNCHER_DEFAULT_WIDTH, LAUNCHER_DEFAULT_HEIGHT).also {
                it.stylesheets.add(DEFAULT_STYLESHEET_PATH)
            }
            launcher.title = resourceBundle.getString(LAUNCHER_TITLE_KEY)
        }

        launcherState.getShownState().subscribe {
            if (it == StageShownState.SHOWN) {
                launcher.show()
            } else {
                launcher.hide()
            }
        }
    }

    override fun showStage() {
        launcherState.setShownState(StageShownState.SHOWN)
    }

    override fun hideStage() {
        launcherState.setShownState(StageShownState.HIDDEN)
    }

    override fun getStageState(): StageShownState = if (launcher.isShowing) {
        StageShownState.SHOWN
    } else {
        StageShownState.HIDDEN
    }
}
