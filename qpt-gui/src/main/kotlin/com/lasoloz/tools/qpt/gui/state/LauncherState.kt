package com.lasoloz.tools.qpt.gui.state

import com.lasoloz.tools.qpt.gui.stage.StageShownState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Launcher state and its observables
 */
@JvmSuppressWildcards
class LauncherState /*@Inject constructor()*/ {
    private val shownState = BehaviorSubject.createDefault(StageShownState.HIDDEN)

    /**
     * Set new stage shown state
     *
     * @param state Stage shown state
     */
    fun setShownState(state: StageShownState) {
        shownState.onNext(state)
    }

    /**
     * Get stage shown state observable
     *
     * @return Observable of the stage shown state
     */
    fun getShownState(): Observable<StageShownState> = shownState
}
