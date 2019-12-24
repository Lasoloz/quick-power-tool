package com.lasoloz.tools.qpt.gui.core

import javafx.scene.control.TextField

/**
 * Text field with persistent prompt until writing some characters
 *
 * Taken from: https://stackoverflow.com/a/25127540/3573460
 */
class PersistentPromptTextField : TextField() {
    init {
        styleClass.add("persistent-prompt")
        refreshPromptVisibility()
        textProperty().addListener { _ ->
            refreshPromptVisibility()
        }
    }

    private fun refreshPromptVisibility() {
        val text = text
        if (isEmptyString(text)) {
            styleClass.remove("no-prompt")
        } else {
            if (!styleClass.contains("no-prompt")) {
                styleClass.add("no-prompt")
            }
        }
    }

    private fun isEmptyString(text: String?): Boolean {
        return text == null || text.isEmpty()
    }
}
