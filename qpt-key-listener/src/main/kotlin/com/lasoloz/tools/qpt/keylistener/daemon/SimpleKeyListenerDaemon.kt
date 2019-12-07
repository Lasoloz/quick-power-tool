package com.lasoloz.tools.qpt.keylistener.daemon

import com.lasoloz.tools.qpt.actions.Action
import com.lasoloz.tools.qpt.actions.ActionStatus
import com.lasoloz.tools.qpt.keylistener.configuration.KeyStrokeMap
import com.tulskiy.keymaster.common.HotKey
import com.tulskiy.keymaster.common.Provider
import java.util.concurrent.atomic.AtomicBoolean
import javax.swing.KeyStroke

class SimpleKeyListenerDaemon(private val keyStrokeMap: KeyStrokeMap) : KeyListenerDaemon {
    private lateinit var thread: Thread
    private var running = AtomicBoolean(false)
    private var cleanedUp = true

    override fun run(): DaemonStatus = if (running.get() || !cleanedUp) {
        DaemonStatus.ALREADY_STARTED
    } else {
        thread = Thread {
            daemon()
        }.apply {
            isDaemon = true
            start()
        }
        Thread.sleep(STARTUP_SLEEP_TIME)
        if (thread.isAlive) DaemonStatus.RUNNING else DaemonStatus.START_FAILURE
    }

    override fun getStatus(): DaemonStatus = if (running.get()) DaemonStatus.RUNNING else DaemonStatus.STOPPED

    override fun tryStop(): DaemonStatus {
        running.set(false)
        thread.join(1000)
        return if (thread.isAlive) {
            cleanedUp = false
            DaemonStatus.STOP_REQUESTED
        } else {
            cleanedUp = true
            DaemonStatus.STOPPED
        }
    }

    override fun forceStop(): DaemonStatus {
        thread.interrupt()
        cleanedUp = true
        running.set(false)
        return DaemonStatus.STOPPED
    }

    private fun daemon() {
        val provider = createProviderWithRegistrations()

        while (running.get()) {
            Thread.sleep(250)
        }

        cleanup(provider)
    }

    private fun createProviderWithRegistrations(): Provider = Provider.getCurrentProvider(false).apply {
        keyStrokeMap.forEach { (keyStroke, action) ->
            register(KeyStroke.getKeyStroke(keyStroke), createLoggedAction(action))
        }
    }

    private fun cleanup(provider: Provider) {
        provider.reset()
        provider.stop()
    }

    private fun createLoggedAction(action: Action): (HotKey) -> Unit = {
        try {
            action.doAction().also {
                when (it) {
                    // TODO: Log these `logically`!
                    is ActionStatus.Success -> println("+ $it")
                    is ActionStatus.Failure -> println("Failure: $it")
                }
            }
        } catch (ex: RuntimeException) {
            println("Runtime exception: ${ex.message}")
        }
    }
}

private const val STARTUP_SLEEP_TIME = 200L
