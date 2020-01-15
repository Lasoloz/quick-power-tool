package com.lasoloz.tools.qpt.gui.launcher.component.results

import com.lasoloz.tools.qpt.actions.ActionConfig
import javafx.beans.NamedArg
import javafx.scene.control.Label
import javafx.scene.layout.VBox

/**
 * Result entry component for action configurations
 *
 * @param actionConfig Action configuration provided to the result entry
 */
class ResultActionConfigComponent(
    @NamedArg("actionConfig") private val actionConfig: ActionConfig
) : VBox(), ResultEntry {
    init {
        prefWidth = 578.0
        styleClass.addAll("base-transparent", "result-entry", "result-action-config")
        with(children) {
            add(createPrefWidthLabel(actionConfig.getNameInfo().name, "result-action-config-name"))
            add(createPrefWidthLabel(actionConfig.getNameInfo().description))
        }
    }

    override fun performAction() {
        actionConfig.getAction().doAction()
    }

    fun selectAction() {
        styleClass.add("result-action-config-selected")
    }

    fun deselectAction() {
        styleClass.remove("result-action-config-selected")
    }

    private fun createPrefWidthLabel(message: String, vararg styleClasses: String): Label {
        return Label(message).also {
            it.prefWidth = 578.0
            it.styleClass.addAll(styleClasses)
        }
    }
}
