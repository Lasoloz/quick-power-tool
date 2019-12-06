package com.lasoloz.tools.qpt.actions

sealed class ActionStatus(val message: String) {
    class Success(message: String) : ActionStatus(message)
    class Failure(message: String) : ActionStatus(message)
}
