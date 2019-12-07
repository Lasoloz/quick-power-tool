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
     * Try stopping the thread as soon as possible (this must be called from a different thread or from a signal hook)
     */
    fun stop()
}
