package com.lasoloz.tools.qpt.coreutils.util

import com.google.inject.Inject

/**
 * Run every setup unit of the application
 *
 * @note Might be removed later on, if it becomes obsolete with the aggregated type
 */
@JvmSuppressWildcards
class SetupRunner @Inject constructor(private val setupUnits: Set<SetupUnit>) {
    /**
     * Call the setup method of every registered setup unit
     */
    fun setupModules() {
        setupUnits.forEach(SetupUnit::setup)
    }
}
