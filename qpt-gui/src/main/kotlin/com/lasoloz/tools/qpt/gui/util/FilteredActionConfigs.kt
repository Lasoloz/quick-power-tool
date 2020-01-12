package com.lasoloz.tools.qpt.gui.util

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.loader.ActionConfigList
import com.lasoloz.tools.qpt.actions.util.ActionConstants.Injection.ACTION_CONFIG_LIST_NAME_KEY
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Filtered action configurations
 *
 * @param actionConfigs Unfiltered action configurations
 */
class FilteredActionConfigs @Inject constructor(
    @Named(ACTION_CONFIG_LIST_NAME_KEY) actionConfigs: ActionConfigList
) {
    inner class ActionConfigListOnFilter internal constructor(
        val actionConfig: Iterable<ActionConfig>,
        val filter: String
    ) {
        operator fun component1(): Iterable<ActionConfig> = actionConfig
        operator fun component2(): String = filter
    }

    private var lastFilter = ""

    private val filterStringSubject: Subject<String> = PublishSubject.create()
    private val distinctFilterStringObservable = filterStringSubject.filter(this::latestDistinctStateFilter)
    private val filteredActionConfigsObservable = combineActionConfigsWithFiltering(actionConfigs)

    /**
     * Set next filter string
     *
     * @param filter New filter string
     */
    fun nextFilter(filter: String) {
        filterStringSubject.onNext(filter)
    }

    /**
     * Get filtered action configurations
     *
     * @return Action configurations filtered as observable
     */
    fun observeFilteredActionConfigs(): Observable<ActionConfigListOnFilter> = filteredActionConfigsObservable

    private fun combineActionConfigsWithFiltering(actionConfigs: ActionConfigList): Observable<ActionConfigListOnFilter> {
        return Observable.combineLatest(
            actionConfigs.observeActionConfigs(),
            distinctFilterStringObservable,
            BiFunction { configs: Iterable<ActionConfig>, filter: String ->
                configs.filter {
                    it.getNameInfo().name.contains(filter)
                }.to(filter)
            }
        )
    }

    private fun Iterable<ActionConfig>.to(filter: String): ActionConfigListOnFilter =
        ActionConfigListOnFilter(this, filter)

    private fun latestDistinctStateFilter(filter: String): Boolean {
        val ret = filter != lastFilter
        lastFilter = filter
        return ret
    }
}
