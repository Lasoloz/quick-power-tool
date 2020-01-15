package com.lasoloz.tools.qpt.gui.category

import com.lasoloz.tools.qpt.actions.ActionConfig

/**
 * Action categorizer for categorizing actions into different categories (e.g. matching, recent, all)
 */
interface ActionCategorizer {
    /**
     * Provided all the configurations return a subcategory of the action configurations
     *
     * @param actionConfigs Action configurations
     * @returns Category containing all the filtered configurations
     */
    fun categorize(actionConfigs: Iterable<ActionConfig>): ActionCategory
}
