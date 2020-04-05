package com.lasoloz.tools.qpt.actions.loader

import com.google.inject.Inject
import com.lasoloz.tools.qpt.actions.ActionConfig
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Action configuration list observable by other modules and loadable upon configuration updates
 *
 * @param actionConfigLoader Action configuration loader
 */
class ActionConfigList @Inject constructor(private val actionConfigLoader: ActionConfigLoader) {
    private val actionConfigs: Subject<Iterable<ActionConfig>> = BehaviorSubject.createDefault(listOf())

    /**
     * Call action config loader and notify subscribers with the new configuration list
     */
    fun reloadActionConfigs() {
        actionConfigLoader.loadActionConfigs().let {
            // TODO: Replace with logger
            println("Loaded action configs: ${it.size}")
            actionConfigs.onNext(it)
        }
    }

    /**
     * Get back observable of action configurations iterable
     *
     * @return Observable action configurations iterable
     */
    fun observeActionConfigs(): Observable<Iterable<ActionConfig>> = actionConfigs
}
