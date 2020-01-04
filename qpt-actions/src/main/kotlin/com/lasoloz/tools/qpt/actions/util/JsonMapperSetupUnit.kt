package com.lasoloz.tools.qpt.actions.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.lasoloz.tools.qpt.coreutils.util.SetupUnit

/**
 * Json mapper setup unit
 *
 * @note Might be deleted later on if it becomes obsolete with its superclass
 */
class JsonMapperSetupUnit(private val objectMapper: ObjectMapper) : SetupUnit {
    override fun setup() {
//        objectMapper.subtypeResolver.collectAndResolveSubtypesByClass()
    }
}
