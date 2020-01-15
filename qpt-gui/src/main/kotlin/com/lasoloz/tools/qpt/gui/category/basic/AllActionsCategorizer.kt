package com.lasoloz.tools.qpt.gui.category.basic

import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.gui.category.ActionCategorizer
import com.lasoloz.tools.qpt.gui.category.ActionCategory

/**
 * Return all action configurations as is
 */
class AllActionsCategorizer : ActionCategorizer {
    override fun categorize(actionConfigs: Iterable<ActionConfig>): ActionCategory =
        ActionCategory(
            "category.all",
            actionConfigs,
            renderIfMissing = true
        )
}
