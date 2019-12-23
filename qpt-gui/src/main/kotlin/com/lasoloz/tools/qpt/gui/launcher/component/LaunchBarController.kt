package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ToolBar
import java.net.URL
import java.util.*

class LaunchBarController : Initializable {
    private lateinit var launcherState: LauncherState

    @Inject
    fun setLauncherState(launcherState: LauncherState) {
        this.launcherState = launcherState
    }

    @FXML
    var toolbar: ToolBar? = null

    @FXML
    private fun exitButtonAction() {
        launcherState.setShownState(StageShownState.HIDDEN)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        InjectorUtil.getInjector().injectMembers(this)
        toolbar?.prefWidth = 596.0
    }
}
