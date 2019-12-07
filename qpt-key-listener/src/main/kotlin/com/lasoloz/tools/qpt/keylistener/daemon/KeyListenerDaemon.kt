package com.lasoloz.tools.qpt.keylistener.daemon

interface KeyListenerDaemon {
    fun run(): DaemonStatus
    fun getStatus(): DaemonStatus
    fun tryStop(): DaemonStatus
    fun forceStop(): DaemonStatus
}
