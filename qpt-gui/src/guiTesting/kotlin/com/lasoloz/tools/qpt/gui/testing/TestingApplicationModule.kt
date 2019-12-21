package com.lasoloz.tools.qpt.gui.testing

import com.google.inject.AbstractModule
import com.lasoloz.tools.qpt.gui.launcher.LAUNCHER_NAME_KEY
import com.lasoloz.tools.qpt.gui.launcher.LauncherStageProxy
import com.lasoloz.tools.qpt.gui.stage.SimpleStageConfig
import com.lasoloz.tools.qpt.gui.stage.StageConfig

class TestingApplicationModule : AbstractModule() {
    override fun configure() {
        bind(StageConfig::class.java).toInstance(SimpleStageConfig().also {
            it.addStageProxy(LAUNCHER_NAME_KEY, LauncherStageProxy())
        })
    }
}
