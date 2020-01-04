package com.lasoloz.tools.qpt.actions.basic

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.lasoloz.tools.qpt.actions.config.dto.AbstractActionConfigDTO
import com.lasoloz.tools.qpt.actions.config.dto.ActionNameInfoDTO

/**
 * Action configuration DTO for the command action
 */
class CommandActionDTO @JsonCreator constructor(
    @JsonProperty("info") actionNameInfo: ActionNameInfoDTO,
    @JsonProperty("command") val command: String
) : AbstractActionConfigDTO(actionNameInfo)
