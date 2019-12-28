package com.lasoloz.tools.qpt.gui.testing

import com.google.inject.AbstractModule
import com.google.inject.Key
import com.google.inject.name.Names
import com.lasoloz.tools.qpt.coreutils.injection.CoreUtilsModule
import com.lasoloz.tools.qpt.coreutils.platform.ConfigFileResolver
import com.lasoloz.tools.qpt.coreutils.util.CoreConstants
import com.lasoloz.tools.qpt.gui.injection.GuiModule
import com.lasoloz.tools.qpt.injections.InjectorUtil

fun main() {
    with(InjectorUtil) {
        registerModule(object : AbstractModule() {
            override fun configure() {
                bind(String::class.java)
                    .annotatedWith(Names.named(CoreConstants.Injection.RELATIVE_CONFIG_DIRECTORY_NAME_KEY))
                    .toInstance(".can-i-change-this")
            }
        })
        registerModule(CoreUtilsModule())
        registerModule(GuiModule())

        getInjector().getInstance(
            Key.get(
                ConfigFileResolver::class.java,
                Names.named(CoreConstants.Injection.CONFIG_FILE_RESOLVER_NAME_KEY)
            )
        ).resolve("someConfig.json").let {
            println(it.absolutePath)
            println(it.path)
        }
    }
//    thread {
//        Platform.setImplicitExit(false)
//        Application.launch(QptGui::class.java)
//    }.let {
//        println("WORKS!")
//        Thread.sleep(500)
//        Platform.runLater {
//            InjectorUtil.getInjector().getInstance(StageConfig::class.java).stageProxyMap[LAUNCHER_NAME_KEY]?.showStage()
//        }
//        Thread.sleep(2000)
//        println("Implicit exit -> true")
//        Platform.setImplicitExit(true)
//        it.join()
//    }
}
