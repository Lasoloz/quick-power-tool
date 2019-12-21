package com.lasoloz.tools.qpt.gui.stage

/**
 * Stage configurations for an application
 */
interface StageConfig {
    /**
     * Get stage proxies by name
     */
    val stageProxyMap: Map<String, StageProxy>
}
