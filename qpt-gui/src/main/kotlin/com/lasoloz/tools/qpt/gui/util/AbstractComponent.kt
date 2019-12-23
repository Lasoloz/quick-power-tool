package com.lasoloz.tools.qpt.gui.util

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.layout.Pane

abstract class AbstractComponent(resourcePath: String) : Pane() {
    init {
        FXMLLoader(javaClass.getResource(resourcePath)).let { loader ->
            val view = loader.load<Node>()
            children.add(view)
        }
    }
}
