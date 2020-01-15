package com.lasoloz.tools.qpt.actions.config.mapper

import com.lasoloz.tools.qpt.actions.ActionNameInfo
import com.lasoloz.tools.qpt.actions.config.model.ActionNameInfoModel

/**
 * Mapping extension method for action name information models
 *
 * @return Action name information object
 */
fun ActionNameInfoModel.mapToActionNameInfo(): ActionNameInfo = ActionNameInfo(specifier, name, description)
