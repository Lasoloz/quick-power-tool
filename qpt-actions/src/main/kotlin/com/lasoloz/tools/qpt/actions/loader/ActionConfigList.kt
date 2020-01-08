package com.lasoloz.tools.qpt.actions.loader

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.util.ActionConstants.Injection.ACTION_CONFIG_LOADER_NAME_KEY
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Action configuration list observable by other modules and loadable upon configuration updates
 *
 * @param actionConfigLoader Action configuration loader
 */
class ActionConfigList @Inject constructor(
    @Named(ACTION_CONFIG_LOADER_NAME_KEY) private val actionConfigLoader: ActionConfigLoader
) {
    private val actionConfigs: Subject<Iterable<ActionConfig>> = BehaviorSubject.createDefault(listOf())

    /**
     * Call action config loader and notify subscribers with the new configuration list
     */
    fun reloadActionConfigs() {
        actionConfigs.onNext(actionConfigLoader.loadActionConfigs())
    }

    /**
     * Get back observable of action configurations iterable
     *
     * @return Observable action configurations iterable
     */
    fun observeActionConfigs(): Observable<Iterable<ActionConfig>> = actionConfigs
}
