package com.lasoloz.tools.qpt.gui.util

import com.lasoloz.tools.qpt.gui.state.LaunchBarEvent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * GUI related common observables
 */
class GuiObservables {
    private var lastFilter = ""

    private val filterStringSubject: Subject<String> = PublishSubject.create()
    private val distinctFilterStringObservable = filterStringSubject.filter(this::latestDistinctStateFilter)

    private val categoryChangeNotifier: Subject<Unit> = PublishSubject.create()

    private val launchBarEventSubject: Subject<LaunchBarEvent> = PublishSubject.create()

    /**
     * Set next filter string
     *
     * @param filter New filter string
     */
    fun nextTextFilter(filter: String) {
        filterStringSubject.onNext(filter.trim())
    }

    /**
     * Notify category change
     */
    fun notifyCategoryChange() {
        categoryChangeNotifier.onNext(Unit)
    }

    /**
     * Set next launch bar event
     *
     * @param event Launch bar event from launch bar
     */
    fun nextLaunchBarEvent(event: LaunchBarEvent) {
        launchBarEventSubject.onNext(event)
    }

    /**
     * Observable text filter
     *
     * @return Text filter observable
     */
    fun observeTextFilter(): Observable<String> = distinctFilterStringObservable

    /**
     * Observable of category change notifier
     *
     * @return Category change notifier as observable
     */
    fun observeCategoryChangeNotifier(): Observable<Unit> = categoryChangeNotifier

    /**
     * Observable of launch bar event subject
     *
     * @return Launch bar event observable
     */
    fun observeLaunchBarEvent(): Observable<LaunchBarEvent> = launchBarEventSubject

    private fun latestDistinctStateFilter(filter: String): Boolean {
        val ret = filter != lastFilter
        lastFilter = filter
        return ret
    }
}
