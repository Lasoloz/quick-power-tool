package com.lasoloz.tools.qpt.actions.config.mapper

import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.config.dto.AbstractActionConfigDTO

/**
 * Action configuration DTO mapper
 */
interface ActionConfigMapper {
    /**
     * Mapping method
     *
     * @param dto Action configuration DTO
     * @return Mapped action configuration
     */
    fun mapToAction(dto: AbstractActionConfigDTO): ActionConfig
}
