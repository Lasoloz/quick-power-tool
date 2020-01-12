package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.gui.launcher.component.results.ResultSeparatorComponent
import com.lasoloz.tools.qpt.gui.util.FilteredActionConfigs
import com.lasoloz.tools.qpt.gui.util.GuiConstants
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

class SearchResultsController : Initializable {
    @FXML
    lateinit var rootVBox: VBox

    /**
     * Inject filtered action configurations
     *
     * @param filteredActionConfigs Filtered action configurations
     */
    @Inject
    fun injectFilteredActionConfigs(
        @Named(GuiConstants.Injection.FILTERED_ACTION_CONFIGS_NAME_KEY) filteredActionConfigs: FilteredActionConfigs
    ) {
        filteredActionConfigs.observeFilteredActionConfigs().subscribe { (actionConfigs, filter) ->
            Platform.runLater {
                rootVBox.children.clear()
                if (filter != "") {
                    rootVBox.children.add(ResultSeparatorComponent("Matching results"))
                }
            }
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        InjectorUtil.getInjector().injectMembers(this)
    }
}
