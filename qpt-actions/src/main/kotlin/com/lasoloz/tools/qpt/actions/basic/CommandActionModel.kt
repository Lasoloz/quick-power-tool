package com.lasoloz.tools.qpt.actions.basic

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.lasoloz.tools.qpt.actions.config.model.AbstractActionConfigModel
import com.lasoloz.tools.qpt.actions.config.model.ActionNameInfoModel

/**
 * Action configuration model for the command action
 */
class CommandActionModel @JsonCreator constructor(
    @JsonProperty("info") actionNameInfo: ActionNameInfoModel,
    @JsonProperty("command") val command: String
) : AbstractActionConfigModel(actionNameInfo)
