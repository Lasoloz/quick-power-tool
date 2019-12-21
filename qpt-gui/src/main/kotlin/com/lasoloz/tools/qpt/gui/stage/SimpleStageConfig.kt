package com.lasoloz.tools.qpt.gui.stage

/**
 * Simple stage proxy configuration implementation with the possibility of adding stage proxies
 */
class SimpleStageConfig : StageConfig {
    private val internalStageProxyMap = mutableMapOf<String, StageProxy>()

    override val stageProxyMap: Map<String, StageProxy>
        get() = internalStageProxyMap

    /**
     * Add Stage proxy
     *
     * @param name Name of the proxy
     * @param stageProxy Stage proxy to add
     */
    fun addStageProxy(name: String, stageProxy: StageProxy) {
        internalStageProxyMap[name] = stageProxy
    }
}
