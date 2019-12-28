package com.lasoloz.tools.qpt.gui.launcher.component.results

import javafx.beans.NamedArg
import javafx.scene.control.Label
import javafx.scene.layout.HBox

/**
 * Result separator component for separating different results
 */
class ResultSeparatorComponent(@NamedArg("message") private val message: String) : HBox(), ResultEntry {
    init {
        prefWidth = 578.0
        styleClass.addAll("base-transparent", "result-separator")
        children.add(Label(message).also {
            prefWidth = 578.0
        })
    }

    override val isSelectable: Boolean
        get() = false

    override fun performAction() {}
}
