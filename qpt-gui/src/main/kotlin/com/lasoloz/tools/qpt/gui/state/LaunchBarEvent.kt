package com.lasoloz.tools.qpt.gui.state

/**
 * Launch bar events are events propagated from the launch bar towards the search results component, so up, down arrows
 * and enter can be handled
 */
enum class LaunchBarEvent {
    /**
     * Select previous event
     */
    PREVIOUS,

    /**
     * Select next item event
     */
    NEXT,

    /**
     * Apply selection event
     */
    SELECT
}
