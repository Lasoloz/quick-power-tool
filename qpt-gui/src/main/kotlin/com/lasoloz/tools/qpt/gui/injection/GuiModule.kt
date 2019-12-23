package com.lasoloz.tools.qpt.gui.injection

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.lasoloz.tools.qpt.coreutils.resource.ResourceBundleUtil
import com.lasoloz.tools.qpt.gui.util.GUI_RESOURCE_BUNDLE_NAME
import com.lasoloz.tools.qpt.gui.util.MESSAGES_RESOURCE_BUNDLE_PATH
import java.util.*
import javax.inject.Named

/**
 * Guice module for the GUI
 */
@Suppress("unused")
class GuiModule : AbstractModule() {
    override fun configure() {}

    /**
     * Gui module resource bundle provider
     *
     * @return Resource bundle for gui module
     */
    @Provides
    @Named(GUI_RESOURCE_BUNDLE_NAME)
    fun provideGuiResourceBundle(): ResourceBundle = ResourceBundleUtil.getUtf8Bundle(MESSAGES_RESOURCE_BUNDLE_PATH)
}
