package com.lasoloz.tools.qpt.actions.config.dto

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import com.lasoloz.tools.qpt.actions.config.json.ActionConfigTypeIdResolver

/**
 * Abstract configuration DTO used for unmarshalling
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.CUSTOM,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@type"
)
@JsonTypeIdResolver(ActionConfigTypeIdResolver::class)
abstract class AbstractActionConfigDTO(val actionNameInfo: ActionNameInfoDTO)
