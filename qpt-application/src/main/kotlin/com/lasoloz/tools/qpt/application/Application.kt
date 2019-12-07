package com.lasoloz.tools.qpt.application

import com.lasoloz.tools.qpt.actions.Action
import com.lasoloz.tools.qpt.actions.ActionStatus
import com.lasoloz.tools.qpt.keylistener.configuration.KeyStrokeActionsMap
import com.lasoloz.tools.qpt.keylistener.daemon.SimpleKeyListenerDaemon
import javax.swing.KeyStroke

fun main() {
    SimpleKeyListenerDaemon().also {
        Runtime.getRuntime().addShutdownHook(Thread {
            it.stop()
        })
    }.also {
        it.run(createKeyStrokeMap())
    }
}

fun createKeyStrokeMap(): KeyStrokeActionsMap {
    return mapOf(
        KeyStroke.getKeyStroke("control alt shift BACK_QUOTE") to object : Action {
            override fun doAction(): ActionStatus {
                println("Application startup!")
                return ActionStatus.Success("Toggle application visibility")
            }
        }
    )
}
