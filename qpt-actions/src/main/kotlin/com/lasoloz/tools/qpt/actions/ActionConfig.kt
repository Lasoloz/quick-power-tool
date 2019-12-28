package com.lasoloz.tools.qpt.actions

/**
 * Action configuration holding action data after load
 */
interface ActionConfig {
    /**
     * Name information about the action
     */
    fun getNameInfo(): ActionNameInfo

    /**
     * The action itself
     */
    fun getAction(): Action
}
