package com.lasoloz.tools.qpt.gui.category.basic

import com.google.inject.Inject
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.gui.category.ActionCategorizer
import com.lasoloz.tools.qpt.gui.category.ActionCategory
import com.lasoloz.tools.qpt.gui.util.GuiObservables

/**
 * Filter action configurations into matching category
 *
 * @param guiObservables GUI Observables
 */
class MatchingActionsCategorizer @Inject constructor(guiObservables: GuiObservables) : ActionCategorizer {
    private var filter = ""

    init {
        guiObservables.observeTextFilter().subscribe {
            filter = it
            guiObservables.notifyCategoryChange()
        }
    }

    override fun categorize(actionConfigs: Iterable<ActionConfig>): ActionCategory {
        // TODO: Maybe create optimized algorithm for config filtering?
        val filterExists = filter.isNotEmpty()
        return if (filterExists) {
            actionConfigs.filter { actionConfig ->
                actionConfig.getNameInfo().name.contains(filter, ignoreCase = true)
            }
        } else {
            listOf()
        }.let { categorizedActions ->
            ActionCategory("category.matching", categorizedActions, filterExists)
        }
    }
}
