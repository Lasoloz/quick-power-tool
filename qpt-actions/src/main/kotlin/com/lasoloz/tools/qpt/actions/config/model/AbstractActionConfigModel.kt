package com.lasoloz.tools.qpt.actions.config.model

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import com.lasoloz.tools.qpt.actions.config.json.ActionConfigTypeIdResolver

/**
 * Abstract configuration model used for unmarshalling
 *
 * @param actionNameInfo Action name information
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.CUSTOM,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@type"
)
@JsonTypeIdResolver(ActionConfigTypeIdResolver::class)
abstract class AbstractActionConfigModel(val actionNameInfo: ActionNameInfoModel)
