package com.lasoloz.tools.qpt.actions

/**
 * Simple action configuration implementation
 *
 * @param actionNameInfo Action name information
 * @param action Action to be run
 */
class SimpleActionConfig(private val actionNameInfo: ActionNameInfo, private val action: Action) : ActionConfig {
    override fun getNameInfo(): ActionNameInfo = actionNameInfo
    override fun getAction(): Action = action
}
