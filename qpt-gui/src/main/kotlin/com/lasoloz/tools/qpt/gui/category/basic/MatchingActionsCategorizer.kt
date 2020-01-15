package com.lasoloz.tools.qpt.gui.category.basic

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.gui.category.ActionCategorizer
import com.lasoloz.tools.qpt.gui.category.ActionCategory
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.GUI_OBSERVABLES_NAME_KEY
import com.lasoloz.tools.qpt.gui.util.GuiObservables

/**
 * Filter action configurations into matching category
 *
 * @param guiObservables GUI Observables
 */
class MatchingActionsCategorizer @Inject constructor(
    @Named(GUI_OBSERVABLES_NAME_KEY) guiObservables: GuiObservables
) : ActionCategorizer {
    private var filter = ""

    init {
        guiObservables.observeTextFilter().subscribe {
            filter = it
            guiObservables.notifyCategoryChange()
        }
    }

    override fun categorize(actionConfigs: Iterable<ActionConfig>): ActionCategory {
        // TODO: Cleanup!!!
        return if (filter.isNotEmpty()) {
            actionConfigs.filter { actionConfig ->
                actionConfig.getNameInfo().name.startsWith(filter)
            }
        } else {
            listOf()
        }.let {
            ActionCategory("category.matching", it, renderIfMissing = filter != "")
        }
    }
}
