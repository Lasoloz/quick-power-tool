package com.lasoloz.tools.qpt.gui.launcher.component.results

/**
 * Abstract action card component is an always selectable result entry component
 *
 * @param resourcePath FXML path
 */
abstract class AbstractActionCardComponent(resourcePath: String) : AbstractResultEntryComponent(resourcePath) {
    override val isSelectable: Boolean
        get() = true
}
