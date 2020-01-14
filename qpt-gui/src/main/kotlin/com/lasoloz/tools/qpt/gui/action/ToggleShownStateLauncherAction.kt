package com.lasoloz.tools.qpt.gui.action

import com.lasoloz.tools.qpt.actions.Action
import com.lasoloz.tools.qpt.actions.ActionStatus
import com.lasoloz.tools.qpt.gui.stage.StageShownState
import com.lasoloz.tools.qpt.gui.state.LauncherState

/**
 * Action for showing/hiding the launcher
 *
 * @param launcherState Launcher state
 */
class ToggleShownStateLauncherAction(private val launcherState: LauncherState) : Action {
    private var shownState = StageShownState.SHOWN;

    init {
        launcherState.getShownState().subscribe { shownState = it }
    }

    override fun doAction(): ActionStatus = if (shownState == StageShownState.SHOWN) {
        launcherState.setShownState(StageShownState.HIDDEN)
        ActionStatus.Success("Hiding launcher...")
    } else {
        launcherState.setShownState(StageShownState.SHOWN)
        ActionStatus.Success("Showing launcher...")
    }
}
