package com.lasoloz.tools.qpt.keylistener.daemon

import com.lasoloz.tools.qpt.actions.Action
import com.lasoloz.tools.qpt.actions.ActionStatus
import com.lasoloz.tools.qpt.keylistener.configuration.KeyStrokeActionsMap
import com.tulskiy.keymaster.common.HotKey
import com.tulskiy.keymaster.common.Provider
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A simple KeyListenerDaemon implementation
 */
class SimpleKeyListenerDaemon : KeyListenerDaemon {
    private lateinit var keyStrokeActionsMap: KeyStrokeActionsMap
    private lateinit var thread: Thread
    private val running = AtomicBoolean(false)

    override fun run(keyStrokeActionsMap: KeyStrokeActionsMap) {
        this.keyStrokeActionsMap = keyStrokeActionsMap
        this.running.set(true)
        this.thread = Thread(this::daemonMethod).also {
            it.start()
        }
    }

    override fun stop() {
        running.set(false)
        thread.join()
    }

    private fun daemonMethod() {
        val provider = createProviderWithRegistrations()

        while (running.get()) {
            Thread.sleep(250)
        }

        cleanup(provider)
    }

    private fun createProviderWithRegistrations(): Provider = Provider.getCurrentProvider(false).apply {
        keyStrokeActionsMap.forEach { (keyStroke, action) ->
            register(keyStroke, createLoggedAction(action))
        }
    }

    private fun createLoggedAction(action: Action): (HotKey) -> Unit = {
        try {
            action.doAction().also {
                when (it) {
                    // TODO: Log these `logically`!
                    is ActionStatus.Success -> println("+ ${it.message}")
                    is ActionStatus.Failure -> println("Failure: ${it.message}")
                }
            }
        } catch (ex: RuntimeException) {
            println("Runtime exception: ${ex.message}")
        }
    }

    private fun cleanup(provider: Provider) {
        provider.reset()
        provider.stop()
    }
}
