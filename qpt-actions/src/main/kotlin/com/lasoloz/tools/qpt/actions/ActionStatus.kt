package com.lasoloz.tools.qpt.actions

/**
 * Status of an action
 *
 * @param message Message tied to the status
 */
sealed class ActionStatus(val message: String) {
    /**
     * `ActionStatus` type returned in case of success
     *
     * @param message Success message
     */
    class Success(message: String) : ActionStatus(message)

    /**
     * `ActionStatus` type returned in case of failure
     *
     * @param message Failure message
     */
    class Failure(message: String) : ActionStatus(message)
}
