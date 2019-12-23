package com.lasoloz.tools.qpt.gui.testing

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.google.inject.multibindings.MapBinder
import com.lasoloz.tools.qpt.gui.launcher.LAUNCHER_NAME_KEY
import com.lasoloz.tools.qpt.gui.launcher.LauncherStageProxy
import com.lasoloz.tools.qpt.gui.stage.StageConfig
import com.lasoloz.tools.qpt.gui.stage.StageProxy

class TestingApplicationModule : AbstractModule() {
    override fun configure() {
        bindStageProxies()
        bind(StageConfig::class.java).`in`(Scopes.SINGLETON)
    }

    private fun bindStageProxies() {
        MapBinder.newMapBinder(binder(), String::class.java, StageProxy::class.java)
            .addBinding(LAUNCHER_NAME_KEY).to(LauncherStageProxy::class.java)
    }
}
