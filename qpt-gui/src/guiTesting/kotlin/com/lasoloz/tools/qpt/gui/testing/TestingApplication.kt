package com.lasoloz.tools.qpt.gui.testing

import com.google.inject.AbstractModule
import com.google.inject.Key
import com.google.inject.name.Names
import com.google.inject.util.Modules
import com.lasoloz.tools.qpt.actions.injection.ActionsModule
import com.lasoloz.tools.qpt.actions.loader.ActionConfigList
import com.lasoloz.tools.qpt.actions.loader.ActionConfigLoader
import com.lasoloz.tools.qpt.actions.util.ActionConstants.Injection.ACTION_CONFIG_LIST_NAME_KEY
import com.lasoloz.tools.qpt.actions.util.ActionLoadException
import com.lasoloz.tools.qpt.coreutils.injection.CoreUtilsModule
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants
import com.lasoloz.tools.qpt.gui.QptGui
import com.lasoloz.tools.qpt.gui.injection.GuiModule
import com.lasoloz.tools.qpt.gui.launcher.LauncherConstants.Injection.LAUNCHER_NAME_KEY
import com.lasoloz.tools.qpt.gui.stage.StageConfig
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.GUI_OBSERVABLES_NAME_KEY
import com.lasoloz.tools.qpt.gui.util.GuiObservables
import com.lasoloz.tools.qpt.injections.InjectorUtil
import javafx.application.Application
import javafx.application.Platform
import kotlin.concurrent.thread

fun main() {
    with(InjectorUtil) {
        val coreUtilsModule = Modules.override(CoreUtilsModule()).with(object : AbstractModule() {
            override fun configure() {
                bind(String::class.java)
                    .annotatedWith(Names.named(CoreConstants.Injection.RELATIVE_CONFIG_DIRECTORY_NAME_KEY))
                    .toInstance(".quick-power-tool") // Actually this is the default
            }
        })
        registerModule(coreUtilsModule)
        registerModule(ActionsModule())
        registerModule(GuiModule())

        try {
            getInjector().getInstance(ActionConfigLoader::class.java).loadActionConfigs().let { list ->
                list.forEach {
                    println(it.getNameInfo())
                }
            }
        } catch (ex: ActionLoadException) {
            println("Action load exception: ${ex.message}")
        } catch (ex: Exception) { // Catch MismatchedInputException
            println("Other eventual exception: ${ex.message}")
        }
    }
    thread {
        Platform.setImplicitExit(false)
        Application.launch(QptGui::class.java)
    }.let {
        println("WORKS!")
        Thread.sleep(500)
        Platform.runLater {
            InjectorUtil.getInjector()
                .getInstance(StageConfig::class.java)
                .stageProxyMap[LAUNCHER_NAME_KEY]
                ?.showStage()
        }
        Thread.sleep(2000)
        InjectorUtil.getInjector()
            .getInstance(Key.get(ActionConfigList::class.java, Names.named(ACTION_CONFIG_LIST_NAME_KEY)))
            .reloadActionConfigs()
        InjectorUtil.getInjector()
            .getInstance(Key.get(GuiObservables::class.java, Names.named(GUI_OBSERVABLES_NAME_KEY)))
            .notifyCategoryChange()
        println("Implicit exit -> true")
        Platform.setImplicitExit(true)
        it.join()
    }
}
