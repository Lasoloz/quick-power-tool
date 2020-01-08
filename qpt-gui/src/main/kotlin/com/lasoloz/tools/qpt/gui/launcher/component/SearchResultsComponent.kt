package com.lasoloz.tools.qpt.gui.launcher.component

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.Paths.SEARCH_RESULT_RESOURCE_PATH
import com.lasoloz.tools.qpt.gui.util.AbstractGuiComponent
import com.lasoloz.tools.qpt.gui.util.FilteredActionConfigs
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.FILTERED_ACTION_CONFIGS_NAME_KEY
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Search result component for showing search result of configuration lookups
 */
class SearchResultsComponent : AbstractGuiComponent(SEARCH_RESULT_RESOURCE_PATH), Initializable {
    /**
     * Inject filtered action configurations
     *
     * @param filteredActionConfigs Filtered action configurations
     */
    @Inject
    fun injectFilteredActionConfigs(
        @Named(FILTERED_ACTION_CONFIGS_NAME_KEY) filteredActionConfigs: FilteredActionConfigs
    ) {
        filteredActionConfigs.observeFilteredActionConfigs().subscribe { (actionConfigs, filter) ->
            children.clear() // TODO: Make this remove stuff!!!
            if (filter != "") {
                // FIXME: The state might be more complex than this :/ ("matching", "recent", "all")
            }
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        InjectorUtil.getInjector().injectMembers(this)
    }
}
