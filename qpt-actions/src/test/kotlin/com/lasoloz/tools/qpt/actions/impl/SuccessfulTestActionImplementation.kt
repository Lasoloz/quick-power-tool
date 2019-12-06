package com.lasoloz.tools.qpt.actions.impl

import com.lasoloz.tools.qpt.actions.Action
import com.lasoloz.tools.qpt.actions.ActionStatus

class SuccessfulTestActionImplementation : Action {
    override fun doAction(): ActionStatus = ActionStatus.Success(ALWAYS_SUCCESS_MESSAGE)
}

const val ALWAYS_SUCCESS_MESSAGE = "Always successful!"
