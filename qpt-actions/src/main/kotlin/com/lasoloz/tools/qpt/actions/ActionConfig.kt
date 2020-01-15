package com.lasoloz.tools.qpt.actions

/**
 * Action configuration holding action data after load
 */
interface ActionConfig {
    /**
     * Name information about the action
     *
     * @return Name information
     */
    fun getNameInfo(): ActionNameInfo

    /**
     * The action itself
     *
     * @return Action
     */
    fun getAction(): Action
}
