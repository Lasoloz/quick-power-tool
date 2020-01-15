package com.lasoloz.tools.qpt.application

import com.google.inject.Key
import com.google.inject.name.Names
import com.lasoloz.tools.qpt.actions.injection.ActionsModule
import com.lasoloz.tools.qpt.actions.loader.ActionConfigList
import com.lasoloz.tools.qpt.actions.util.ActionConstants.Injection.ACTION_CONFIG_LIST_NAME_KEY
import com.lasoloz.tools.qpt.coreutils.injection.CoreUtilsModule
import com.lasoloz.tools.qpt.gui.QptGui
import com.lasoloz.tools.qpt.gui.action.ToggleShownStateLauncherAction
import com.lasoloz.tools.qpt.gui.injection.GuiModule
import com.lasoloz.tools.qpt.gui.state.LauncherState
import com.lasoloz.tools.qpt.injections.InjectorUtil
import com.lasoloz.tools.qpt.keylistener.configuration.KeyStrokeActionsMap
import com.lasoloz.tools.qpt.keylistener.daemon.SimpleKeyListenerDaemon
import javafx.application.Application
import javafx.application.Platform
import javax.swing.KeyStroke

fun main() {
    setupInjection()
    val keyStrokeMap = setupKeyStrokeMap()
    setupKeyListenerDaemon(keyStrokeMap)
    loadConfigs()
    setupGui()
}

private fun setupInjection() {
    with(InjectorUtil) {
        registerModule(CoreUtilsModule())
        registerModule(ActionsModule())
        registerModule(GuiModule())
    }
}

private fun setupKeyStrokeMap(): KeyStrokeActionsMap {
    val launcherState = InjectorUtil.getInjector().getInstance(LauncherState::class.java)

    return mapOf(
        KeyStroke.getKeyStroke("control alt shift BACK_QUOTE") to ToggleShownStateLauncherAction(launcherState)
    )
}

private fun setupKeyListenerDaemon(keyStrokeMap: KeyStrokeActionsMap) {
    SimpleKeyListenerDaemon().also { daemon ->
        Runtime.getRuntime().addShutdownHook(Thread {
            daemon.stop()
        })
    }.also { daemon ->
        daemon.run(keyStrokeMap)
    }
}

private fun loadConfigs() {
    InjectorUtil.getInjector()
        .getInstance(Key.get(ActionConfigList::class.java, Names.named(ACTION_CONFIG_LIST_NAME_KEY)))
        .reloadActionConfigs()
}

private fun setupGui() {
    Platform.setImplicitExit(false)
    Application.launch(QptGui::class.java)
}
