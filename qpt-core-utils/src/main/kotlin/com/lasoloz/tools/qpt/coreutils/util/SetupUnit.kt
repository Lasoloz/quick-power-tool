package com.lasoloz.tools.qpt.coreutils.util

/**
 * Setup unit can be implemented by different modules to append module specific logic to the setup stage
 */
interface SetupUnit {
    /**
     * Setup method
     */
    fun setup()
}
