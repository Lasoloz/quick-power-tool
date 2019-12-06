package com.lasoloz.tools.qpt.keylistener.daemon

import com.lasoloz.tools.qpt.keylistener.configuration.KeyStrokeMap

interface KeyListenerDaemon {
    fun run(configuration: KeyStrokeMap): DaemonStatus
    fun getStatus(): DaemonStatus
    fun tryStop(): DaemonStatus
    fun forceStop(): DaemonStatus
}
