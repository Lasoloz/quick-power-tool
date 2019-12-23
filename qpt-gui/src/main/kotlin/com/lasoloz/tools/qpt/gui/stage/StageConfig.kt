package com.lasoloz.tools.qpt.gui.stage

import com.google.inject.Inject

/**
 * Stage configurations for an application
 */
@JvmSuppressWildcards
data class StageConfig @Inject constructor(val stageProxyMap: Map<String, StageProxy>)
