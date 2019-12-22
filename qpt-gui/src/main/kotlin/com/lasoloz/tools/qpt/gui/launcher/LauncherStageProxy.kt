package com.lasoloz.tools.qpt.gui.launcher

import com.lasoloz.tools.qpt.gui.stage.StageProxy
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle

/**
 * Stage proxy implementation for the launcher stage
 */
class LauncherStageProxy : StageProxy {
    private lateinit var launcher: Stage

    override fun initStage() {
        FXMLLoader.load<Parent>(javaClass.getResource(LAUNCHER_RESOURCE_PATH)).let { root ->
            launcher = Stage(StageStyle.UNDECORATED)
            launcher.scene = Scene(root, LAUNCHER_DEFAULT_WIDTH, LAUNCHER_DEFAULT_HEIGHT)
            launcher.title = LAUNCHER_TITLE_KEY
        }
    }

    override fun showStage() {
        launcher.show()
    }

    override fun hideStage() {
        launcher.hide()
    }

    override fun getStageState(): StageShownState = if (launcher.isShowing) {
        StageShownState.SHOWN
    } else {
        StageShownState.HIDDEN
    }
}
