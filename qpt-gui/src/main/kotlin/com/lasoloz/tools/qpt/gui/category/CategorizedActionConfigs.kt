package com.lasoloz.tools.qpt.gui.category

import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.ActionConfig
import com.lasoloz.tools.qpt.actions.loader.ActionConfigList
import com.lasoloz.tools.qpt.actions.util.ActionConstants.Injection.ACTION_CONFIG_LIST_NAME_KEY
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.ACTION_CATEGORIZERS_NAME_KEY
import com.lasoloz.tools.qpt.gui.util.GuiConstants.Injection.GUI_OBSERVABLES_NAME_KEY
import com.lasoloz.tools.qpt.gui.util.GuiObservables
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * Categorized action configuration observables
 *
 * @param actionConfigs Observable action configurations
 * @param actionCategorizers Action categorizers used for categorization
 * @param guiObservables GUI observables
 */
@JvmSuppressWildcards
class CategorizedActionConfigs @Inject constructor(
    @Named(ACTION_CONFIG_LIST_NAME_KEY) private val actionConfigs: ActionConfigList,
    @Named(ACTION_CATEGORIZERS_NAME_KEY) private val actionCategorizers: Set<ActionCategorizer>,
    @Named(GUI_OBSERVABLES_NAME_KEY) private val guiObservables: GuiObservables
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
            guiObservables.observeCategoryChangeNotifier(),
            BiFunction { actionConfigs: Iterable<ActionConfig>, _: Unit ->
                actionCategorizers.reversed().map { categorizer -> categorizer.categorize(actionConfigs) }
            }
        )
    }
}
