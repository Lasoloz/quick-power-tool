package com.lasoloz.tools.qpt.actions.config.mapper

import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.config.model.AbstractActionConfigModel

/**
 * Action configuration model mapper
 */
interface ActionConfigMapper {
    /**
     * Mapping method
     *
     * @param model Action configuration model
     * @return Mapped action configuration
     */
    fun mapToAction(model: AbstractActionConfigModel): ActionConfig
}
