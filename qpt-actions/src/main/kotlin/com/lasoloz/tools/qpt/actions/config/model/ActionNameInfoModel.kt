package com.lasoloz.tools.qpt.actions.config.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Action name info model used for serialization and deserialization
 *
 * @param specifier As in [ActionNameInfoModel]
 * @param name As in [ActionNameInfoModel]
 * @param description As in [ActionNameInfoModel]
 */
data class ActionNameInfoModel @JsonCreator constructor(
    @JsonProperty val specifier: String,
    @JsonProperty val name: String,
    @JsonProperty val description: String
)
