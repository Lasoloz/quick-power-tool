package com.lasoloz.tools.qpt.gui.util

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

    /**
     * Set next filter string
     *
     * @param filter New filter string
     */
    fun nextTextFilter(filter: String) {
        filterStringSubject.onNext(filter.trim())
    }

    /**
     * Observable text filter
     *
     * @return Text filter observable
     */
    fun observeTextFilter(): Observable<String> = distinctFilterStringObservable

    private fun latestDistinctStateFilter(filter: String): Boolean {
        val ret = filter != lastFilter
        lastFilter = filter
        return ret
    }
}
