package com.lasoloz.tools.qpt.gui

import com.lasoloz.tools.qpt.gui.stage.StageConfig
import com.lasoloz.tools.qpt.gui.stage.StageProxy
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.application.Application
import javafx.stage.Stage

class QptGui : Application() {
    private lateinit var stageConfig: StageConfig

    override fun start(ignoredStage: Stage) {
        InjectorUtil.getInjector().getInstance(StageConfig::class.java).let { config ->
            stageConfig = config
            config.stageProxyMap.values.forEach(StageProxy::initStage)
        }
    }
}
