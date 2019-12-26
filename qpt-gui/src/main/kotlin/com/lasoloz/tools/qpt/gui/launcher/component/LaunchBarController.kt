package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.lasoloz.tools.qpt.gui.core.PersistentPromptTextField
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import java.net.URL
import java.util.*

class LaunchBarController : Initializable {
    private lateinit var launcherState: LauncherState

    @FXML
    private lateinit var textField: PersistentPromptTextField

    @Inject
    fun setLauncherState(launcherState: LauncherState) {
        this.launcherState = launcherState
    }

    @FXML
    private fun keyOnReleaseAction(e: KeyEvent) {
        println("TODO: Look up text value ${textField.text}")
    }

    @FXML
    private fun exitButtonAction() {
        launcherState.setShownState(StageShownState.HIDDEN)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        InjectorUtil.getInjector().injectMembers(this)
        addUpDownFilter()
    }

    private fun addUpDownFilter() {
        textField.addEventFilter(KeyEvent.ANY) { event ->
            if (event.code == KeyCode.UP) {
                event.doOnRelease {
                    println("TODO: Select previous")
                }
                event.consume()
            } else if (event.code == KeyCode.DOWN) {
                event.doOnRelease {
                    println("TODO: Select next")
                }
                event.consume()
            }
        }
    }

    private fun KeyEvent.doOnRelease(action: () -> Unit) {
        if (eventType == KeyEvent.KEY_RELEASED) {
            action()
        }
    }
}
