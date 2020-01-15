package com.lasoloz.tools.qpt.gui.category

import com.lasoloz.tools.qpt.actions.ActionConfig

/**
 * Action category with a category name (e.g. "Matching results") and its related action configurations
 *
 * @param name Name of the category
 * @param actionConfigs Action configurations listed by this category
 * @param renderIfMissing Can category be rendered, if missing?
 */
data class ActionCategory(
    val name: String,
    val actionConfigs: Iterable<ActionConfig>,
    val renderIfMissing: Boolean = false
)
