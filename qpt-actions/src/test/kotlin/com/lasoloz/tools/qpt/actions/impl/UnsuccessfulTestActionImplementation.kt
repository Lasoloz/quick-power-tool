package com.lasoloz.tools.qpt.actions.impl

import com.lasoloz.tools.qpt.actions.Action
import com.lasoloz.tools.qpt.actions.ActionStatus

class UnsuccessfulTestActionImplementation : Action {
    override fun doAction(): ActionStatus = ActionStatus.Failure(ALWAYS_UNSUCCESSFUL_MESSAGE)
}

const val ALWAYS_UNSUCCESSFUL_MESSAGE = "Always unsuccessful!"
