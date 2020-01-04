package com.lasoloz.tools.qpt.actions.basic

import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.SimpleActionConfig
import com.lasoloz.tools.qpt.actions.config.dto.AbstractActionConfigDTO
import com.lasoloz.tools.qpt.actions.config.mapper.ActionConfigMapper
import com.lasoloz.tools.qpt.actions.config.mapper.mapToActionNameInfo
import com.lasoloz.tools.qpt.actions.util.ActionLoadException

/**
 * Command action mapper
 */
class CommandActionMapper : ActionConfigMapper {
    override fun mapToAction(dto: AbstractActionConfigDTO): ActionConfig {
        if (dto is CommandActionDTO) {
            return SimpleActionConfig(dto.actionNameInfo.mapToActionNameInfo(), CommandAction(dto.command))
        }
        throw ActionLoadException("Incorrect DTO type provided for mapper!")
    }
}
