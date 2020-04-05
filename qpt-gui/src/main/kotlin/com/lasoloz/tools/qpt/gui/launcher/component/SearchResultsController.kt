package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.gui.category.ActionCategory
import com.lasoloz.tools.qpt.gui.category.CategorizedActionConfigs
import com.lasoloz.tools.qpt.gui.launcher.component.results.ResultActionConfigComponent
import com.lasoloz.tools.qpt.gui.launcher.component.results.ResultSeparatorComponent
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import com.lasoloz.tools.qpt.gui.state.LaunchBarEvent
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.gui.util.GuiObservables
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

/**
 * Controller for search result component
 */
class SearchResultsController : Initializable {
    @FXML
    private lateinit var rootVBox: VBox

    private lateinit var launcherState: LauncherState

    private var resources: ResourceBundle? = null

    private val actionConfigList = mutableListOf<ResultActionConfigComponent>()
    private var selection = 0

    /**
     * Inject categorized action configs
     *
     * @param categorizedActionConfigs Action configurations categorized
     */
    @Inject
    fun injectCategorizedActionConfigs(categorizedActionConfigs: CategorizedActionConfigs) {
        categorizedActionConfigs.observeCategorizedActions().subscribe { actionCategories ->
            Platform.runLater {
                rootVBox.children.clear()
                actionConfigList.clear()
                actionCategories.forEach(this::processCategory)
                selection = 0
                selectCurrent()
            }
        }
    }

    /**
     * Inject GUI observables for event processing
     *
     * @param guiObservables GUI observables
     */
    @Inject
    fun injectGuiObservables(guiObservables: GuiObservables) {
        guiObservables.observeLaunchBarEvent().subscribe { event ->
            when (event) {
                LaunchBarEvent.PREVIOUS -> selectPrevious()
                LaunchBarEvent.NEXT -> selectNext()
                LaunchBarEvent.SELECT -> callSelection()
                else -> {
                }
            }
        }
    }

    /**
     * Inject launcher state
     *
     * @param launcherState Injected launcher state
     */
    @Inject
    fun injectLauncherState(launcherState: LauncherState) {
        this.launcherState = launcherState
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.resources = resources
        InjectorUtil.getInjector().injectMembers(this)
    }

    private fun processCategory(actionCategory: ActionCategory) {
        if (actionCategory.actionConfigs.any() || actionCategory.renderIfMissing) {
            createCategorySeparator(actionCategory.name)
        }
        actionCategory.actionConfigs.forEach(this::processActionConfig)
    }

    private fun createCategorySeparator(name: String) {
        ResultSeparatorComponent(resources?.getString(name) ?: name).also {
            rootVBox.children.add(it)
        }
    }

    private fun processActionConfig(actionConfig: ActionConfig) {
        ResultActionConfigComponent(actionConfig).let { component ->
            rootVBox.children.add(component)
            actionConfigList.add(component)
        }
    }

    private fun selectNext() {
        if (actionsPresent()) {
            deselectCurrent()
            selection = (selection + 1) % actionConfigList.size
            selectCurrent()
        }
    }

    private fun selectPrevious() {
        if (actionsPresent()) {
            deselectCurrent()
            selection = Math.floorMod(selection - 1, actionConfigList.size)
            selectCurrent()
        }
    }

    private fun selectCurrent() {
        if (actionsPresent()) {
            actionConfigList[selection].selectAction()
        }
    }

    private fun deselectCurrent() {
        if (actionsPresent()) {
            actionConfigList[selection].deselectAction()
        }
    }

    private fun callSelection() {
        if (actionsPresent()) {
            actionConfigList[selection].performAction()
            launcherState.setShownState(StageShownState.HIDDEN)
        }
    }

    private fun actionsPresent(): Boolean = actionConfigList.isNotEmpty()
}
