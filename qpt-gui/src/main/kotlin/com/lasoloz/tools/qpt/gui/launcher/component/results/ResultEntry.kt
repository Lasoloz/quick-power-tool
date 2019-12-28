package com.lasoloz.tools.qpt.gui.launcher.component.results

/**
 * Result entry interface of search results
 */
interface ResultEntry {
    /**
     * Check if entry is selectable on list
     */
    val isSelectable: Boolean

    /**
     * Perform action upon selection
     */
    fun performAction()
}
