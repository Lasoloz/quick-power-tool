package com.lasoloz.tools.qpt.gui.launcher.component.results

import com.lasoloz.tools.qpt.actions.ActionConfig
import javafx.beans.NamedArg
import javafx.scene.control.Label
import javafx.scene.layout.HBox

/**
 * Result entry component for action configurations
 *
 * @param actionConfig Action configuration provided to the result entry
 */
class ResultActionConfigComponent(
    @NamedArg("actionConfig") val actionConfig: ActionConfig
) : HBox(), ResultEntry {
    init {
        prefWidth = 578.0
        styleClass.addAll("base-transparent", "result-entry", "result-action-config")
        children.add(Label(actionConfig.getNameInfo().toString()).also {
            prefWidth = 578.0
        })
    }

    override val isSelectable: Boolean
        get() = true

    override fun performAction() {
        actionConfig.getAction().doAction()
    }
}
