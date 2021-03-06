package com.lasoloz.tools.qpt.gui.util

import com.google.inject.Key
import com.google.inject.name.Names
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.layout.Pane
import java.util.*

/**
 * Abstract component enclosing gui specific component creation
 *
 * @param resourcePath Path to the FXML resource
 */
abstract class AbstractGuiComponent(resourcePath: String) : Pane() {
    init {
        val resources = InjectorUtil.getInjector()
            .getInstance(
                Key.get(ResourceBundle::class.java, Names.named(GuiConstants.Injection.GUI_RESOURCE_NAME_KEY))
            )
        FXMLLoader(javaClass.getResource(resourcePath), resources).let { loader ->
            val view = loader.load<Node>()
            children.add(view)
        }
    }
}
