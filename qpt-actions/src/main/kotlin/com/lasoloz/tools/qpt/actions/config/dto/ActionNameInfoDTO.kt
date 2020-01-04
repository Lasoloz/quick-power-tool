package com.lasoloz.tools.qpt.actions.config.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Action name info DTO used for serialization and deserialization
 *
 * @param specifier As in [ActionNameInfoDTO]
 * @param name As in [ActionNameInfoDTO]
 * @param description As in [ActionNameInfoDTO]
 */
data class ActionNameInfoDTO @JsonCreator constructor(
    @JsonProperty val specifier: String,
    @JsonProperty val name: String,
    @JsonProperty val description: String
)
