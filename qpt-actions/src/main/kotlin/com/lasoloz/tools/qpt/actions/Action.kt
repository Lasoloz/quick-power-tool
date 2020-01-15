package com.lasoloz.tools.qpt.actions

/**
 * Action type is to be run in case of something happening in the application (e.g. keystroke pressed)
 */
interface Action {
    /**
     * Run the action
     *
     * @return Status of the action
     */
    fun doAction(): ActionStatus
}
