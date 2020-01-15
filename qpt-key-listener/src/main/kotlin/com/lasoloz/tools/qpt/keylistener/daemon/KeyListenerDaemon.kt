package com.lasoloz.tools.qpt.keylistener.daemon

import com.lasoloz.tools.qpt.keylistener.configuration.KeyStrokeActionsMap

/**
 * A daemon with the task of listening for key strokes
 */
interface KeyListenerDaemon {
    /**
     * Run daemon indefinitely or until stop is called
     *
     * @param keyStrokeActionsMap Keystrokes assigned to actions
     */
    fun run(keyStrokeActionsMap: KeyStrokeActionsMap)

    /**
     * Wait for and stop the daemon and free up resources
     */
    fun stop()
}
