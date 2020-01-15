package com.lasoloz.tools.qpt.actions.basic

import com.lasoloz.tools.qpt.actions.Action
import com.lasoloz.tools.qpt.actions.ActionStatus
import java.io.InputStreamReader

/**
 * Simple action for running executing a command
 *
 * @param command Command to be run
 */
class CommandAction(private val command: String) : Action {
    override fun doAction(): ActionStatus {
        return Runtime.getRuntime().exec(command).let { process ->
            if (process.isAlive) {
                ActionStatus.Success("Command $command running")
            } else {
                val issue = InputStreamReader(process.inputStream).readText()
                ActionStatus.Failure("Command $command failed! Issue:\n$issue")
            }
        }
    }
}
