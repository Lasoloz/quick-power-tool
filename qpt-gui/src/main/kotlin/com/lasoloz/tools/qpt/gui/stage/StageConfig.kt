package com.lasoloz.tools.qpt.gui.stage

import com.google.inject.Inject

/**
 * Stage configurations for an application
 *
 * @param stageProxyMap Stage proxies used in the provided stage configuration
 */
@JvmSuppressWildcards
data class StageConfig @Inject constructor(val stageProxyMap: Map<String, StageProxy>)
