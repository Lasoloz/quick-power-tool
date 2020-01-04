package com.lasoloz.tools.qpt.actions.config.mapper

import com.lasoloz.tools.qpt.actions.ActionNameInfo
import com.lasoloz.tools.qpt.actions.config.dto.ActionNameInfoDTO

/**
 * Mapping extension method for action name information DTOs
 *
 * @return Action name information object
 */
fun ActionNameInfoDTO.mapToActionNameInfo(): ActionNameInfo = ActionNameInfo(specifier, name, description)
