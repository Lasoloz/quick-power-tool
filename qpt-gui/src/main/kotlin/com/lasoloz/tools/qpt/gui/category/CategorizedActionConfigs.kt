package com.lasoloz.tools.qpt.gui.category

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.loader.ActionConfigList
import com.lasoloz.tools.qpt.actions.util.ActionConstants.Injection.ACTION_CONFIG_LIST_NAME_KEY
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.ACTION_CATEGORIZERS_NAME_KEY
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.CATEGORIZED_ACTIONS_NOTIFIER_NAME_KEY
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.Subject

/**
 * Categorized action configuration observables
 *
 * @param actionConfigs Observable action configurations
 * @param actionCategorizers Action categorizers used for categorization
 * @param categorizedActionNotifier Categorized action notifier for reloading categorizations
 */
@JvmSuppressWildcards
class CategorizedActionConfigs @Inject constructor(
    @Named(ACTION_CONFIG_LIST_NAME_KEY) private val actionConfigs: ActionConfigList,
    @Named(ACTION_CATEGORIZERS_NAME_KEY) private val actionCategorizers: Set<ActionCategorizer>,
    @Named(CATEGORIZED_ACTIONS_NOTIFIER_NAME_KEY) private val categorizedActionNotifier: Subject<Boolean>
) {
    private val categorizedActionsObservable = createCategorizedActionsObservable()

    /**
     * Observe categorized action configurations
     *
     * @return Observable of categorized actions
     */
    fun observeCategorizedActions(): Observable<Iterable<ActionCategory>> = categorizedActionsObservable

    private fun createCategorizedActionsObservable(): Observable<Iterable<ActionCategory>> {
        return Observable.combineLatest(
            actionConfigs.observeActionConfigs(),
            categorizedActionNotifier,
            BiFunction { actionConfigs: Iterable<ActionConfig>, _: Boolean ->
                actionCategorizers.reversed().map { categorizer -> categorizer.categorize(actionConfigs) }
            }
        )
    }
}
