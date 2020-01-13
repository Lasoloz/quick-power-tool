package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.gui.category.ActionCategory
import com.lasoloz.tools.qpt.gui.category.CategorizedActionConfigs
import com.lasoloz.tools.qpt.gui.launcher.component.results.ResultActionConfigComponent
import com.lasoloz.tools.qpt.gui.launcher.component.results.ResultSeparatorComponent
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.CATEGORIZED_ACTION_CONFIGS_NAME_KEY
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

    private var resources: ResourceBundle? = null

    /**
     * Inject categorized action configs
     *
     * @param categorizedActionConfigs Action configurations categorized
     */
    @JvmSuppressWildcards
    @Inject
    fun injectCategorizedActionConfigs(
        @Named(CATEGORIZED_ACTION_CONFIGS_NAME_KEY) categorizedActionConfigs: CategorizedActionConfigs
    ) {
        categorizedActionConfigs.observeCategorizedActions().subscribe { actionCategories ->
            Platform.runLater {
                rootVBox.children.clear()
                actionCategories.forEach(this::renderCategory)
            }
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.resources = resources
        InjectorUtil.getInjector().injectMembers(this)
    }

    private fun renderCategory(actionCategory: ActionCategory) {
        if (actionCategory.actionConfigs.any() || actionCategory.renderIfMissing) {
            createCategorySeparator(actionCategory.name)
        }
        actionCategory.actionConfigs.forEach(this::renderActionConfig)
    }

    private fun createCategorySeparator(name: String) {
        ResultSeparatorComponent(resources?.getString(name) ?: name).also {
            rootVBox.children.add(it)
        }
    }

    private fun renderActionConfig(actionConfig: ActionConfig) {
        rootVBox.children.add(ResultActionConfigComponent(actionConfig))
    }
}
