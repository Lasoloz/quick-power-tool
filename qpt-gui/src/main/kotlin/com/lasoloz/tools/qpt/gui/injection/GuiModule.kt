package com.lasoloz.tools.qpt.gui.injection

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Scopes
import com.google.inject.multibindings.MapBinder
import com.lasoloz.tools.qpt.actions.injection.ActionsModule
import com.lasoloz.tools.qpt.coreutils.resource.ResourceBundleUtil
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants
import com.lasoloz.tools.qpt.gui.launcher.LauncherStageProxy
import com.lasoloz.tools.qpt.gui.stage.StageConfig
import com.lasoloz.tools.qpt.gui.stage.StageProxy
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.gui.util.GuiConstants
import java.util.*
import javax.inject.Named

/**
 * Guice module for the GUI
 */
@Suppress("unused")
class GuiModule : AbstractModule() {
    override fun configure() {
        bindStageProxies()
        bind(StageConfig::class.java).`in`(Scopes.SINGLETON)
        bind(LauncherState::class.java).`in`(Scopes.SINGLETON)
        binder().install(ActionsModule())
    }

    private fun bindStageProxies() {
        MapBinder.newMapBinder(binder(), String::class.java, StageProxy::class.java)
            .addBinding(LauncherConstants.Injection.LAUNCHER_NAME_KEY).to(LauncherStageProxy::class.java)
    }

    /**
     * Gui module resource bundle provider
     *
     * @return Resource bundle for gui module
     */
    @Provides
    @Named(GuiConstants.Injection.GUI_RESOURCE_NAME_KEY)
    fun provideGuiResourceBundle(): ResourceBundle =
        ResourceBundleUtil.getUtf8Bundle(GuiConstants.MESSAGES_RESOURCE_BUNDLE_PATH)
}
