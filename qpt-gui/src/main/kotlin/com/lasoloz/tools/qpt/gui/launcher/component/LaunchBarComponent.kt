package com.lasoloz.tools.qpt.gui.launcher.component

import com.lasoloz.tools.qpt.gui.launcher.LAUNCH_BAR_RESOURCE_PATH
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.layout.Pane

class LaunchBarComponent : Pane() {
    init {
        FXMLLoader(javaClass.getResource(LAUNCH_BAR_RESOURCE_PATH)).let { loader ->
            val view = loader.load<Node>()
            children.add(view)
        }
    }
}
