package com.lasoloz.tools.qpt.gui.injection

import com.google.inject.AbstractModule
import com.lasoloz.tools.qpt.gui.util.MESSAGES_RESOURCE_BUNDLE_PATH
import java.io.InputStreamReader
import java.util.*

class GuiModule : AbstractModule() {
    override fun configure() {
        // TODO: Solve UTF-8 issue & make this use a map with specific keys maybe like in the other?
        bind(ResourceBundle::class.java).toInstance(ResourceBundle.getBundle(MESSAGES_RESOURCE_BUNDLE_PATH))
    }
}
