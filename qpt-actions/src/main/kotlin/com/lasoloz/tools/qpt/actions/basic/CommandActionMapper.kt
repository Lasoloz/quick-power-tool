package com.lasoloz.tools.qpt.actions.basic

import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.SimpleActionConfig
import com.lasoloz.tools.qpt.actions.config.mapper.ActionConfigMapper
import com.lasoloz.tools.qpt.actions.config.mapper.mapToActionNameInfo
import com.lasoloz.tools.qpt.actions.config.model.AbstractActionConfigModel
import com.lasoloz.tools.qpt.actions.util.ActionLoadException

/**
 * Command action mapper
 */
class CommandActionMapper : ActionConfigMapper {
    override fun mapToAction(model: AbstractActionConfigModel): ActionConfig {
        if (model is CommandActionModel) {
            return SimpleActionConfig(model.actionNameInfo.mapToActionNameInfo(), CommandAction(model.command))
        }
        throw ActionLoadException("Incorrect model type provided for mapper!")
    }
}
