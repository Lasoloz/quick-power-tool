package com.lasoloz.tools.qpt.gui.state

import com.lasoloz.tools.qpt.gui.stage.StageShownState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class LauncherState {
    private var shownState = BehaviorSubject.createDefault(StageShownState.HIDDEN)

    fun setShownState(state: StageShownState) {
        shownState.onNext(state)
    }

    fun getShownState(): Observable<StageShownState> = shownState
}
