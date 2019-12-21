package com.lasoloz.tools.qpt.gui.testing

import com.lasoloz.tools.qpt.gui.QptGui
import com.lasoloz.tools.qpt.gui.launcher.LAUNCHER_NAME_KEY
import com.lasoloz.tools.qpt.gui.stage.StageConfig
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.application.Application
import javafx.application.Platform
import kotlin.concurrent.thread

fun main() {
    InjectorUtil.registerModule(TestingApplicationModule())
    thread {
        Platform.setImplicitExit(false)
        Application.launch(QptGui::class.java)
    }.let {
        println("WORKS!")
        Thread.sleep(2000)
        Platform.runLater {
            InjectorUtil.getInjector().getInstance(StageConfig::class.java).stageProxyMap[LAUNCHER_NAME_KEY]?.showStage()
        }
        Thread.sleep(10000)
        println("Implicit exit -> true")
        Platform.setImplicitExit(true)
        it.join()
    }
}
