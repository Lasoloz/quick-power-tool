package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.gui.core.PersistentPromptTextField
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.gui.util.FilteredActionConfigs
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.FILTERED_ACTION_CONFIGS_NAME_KEY
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
    private lateinit var filteredActionConfigs: FilteredActionConfigs

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
        @Named(FILTERED_ACTION_CONFIGS_NAME_KEY) filteredActionConfigs: FilteredActionConfigs
    ) {
        this.filteredActionConfigs = filteredActionConfigs
    }

    @FXML
    private fun keyOnReleaseAction(e: KeyEvent) {
        filteredActionConfigs.nextFilter(textField.text)
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
