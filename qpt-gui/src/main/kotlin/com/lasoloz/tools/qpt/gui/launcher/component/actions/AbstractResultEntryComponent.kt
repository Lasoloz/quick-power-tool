package com.lasoloz.tools.qpt.gui.launcher.component.actions

import com.lasoloz.tools.qpt.gui.util.AbstractGuiComponent

/**
 * Abstract result entry extending abstract GUI component and implementing result entry interface
 *
 * @param resourcePath FXML path
 */
abstract class AbstractResultEntryComponent(resourcePath: String) : AbstractGuiComponent(resourcePath), ResultEntry
