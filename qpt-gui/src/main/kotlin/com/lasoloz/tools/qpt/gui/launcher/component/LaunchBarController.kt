package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.gui.core.PersistentPromptTextField
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import com.lasoloz.tools.qpt.gui.state.LaunchBarEvent
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.gui.util.GuiObservables
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.GUI_OBSERVABLES_NAME_KEY
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import java.net.URL
import java.util.*

/**
 * Launch bar controller
 */
class LaunchBarController : Initializable {
    private lateinit var launcherState: LauncherState
    private lateinit var guiObservables: GuiObservables

    @FXML
    private lateinit var textField: PersistentPromptTextField

    /**
     * Inject launcher state
     *
     * @param launcherState Launcher state
     */
    @Inject
    fun injectLauncherState(launcherState: LauncherState) {
        this.launcherState = launcherState
    }

    @Inject
    fun injectFilteredActionConfigs(
        @Named(GUI_OBSERVABLES_NAME_KEY) guiObservables: GuiObservables
    ) {
        this.guiObservables = guiObservables
    }

    @FXML
    private fun keyOnReleaseAction(e: KeyEvent) {
        guiObservables.nextTextFilter(textField.text)
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
            when (event.code) {
                KeyCode.UP -> {
                    event.doOnRelease {
                        guiObservables.nextLaunchBarEvent(LaunchBarEvent.PREVIOUS)
                    }
                    event.consume()
                }
                KeyCode.DOWN -> {
                    event.doOnRelease {
                        guiObservables.nextLaunchBarEvent(LaunchBarEvent.NEXT)
                    }
                    event.consume()
                }
                KeyCode.ENTER -> {
                    event.doOnRelease {
                        guiObservables.nextLaunchBarEvent(LaunchBarEvent.SELECT)
                    }
                    event.consume()
                }
                else -> {}
            }
        }
    }

    private fun KeyEvent.doOnRelease(action: () -> Unit) {
        if (eventType == KeyEvent.KEY_RELEASED) {
            action()
        }
    }
}
