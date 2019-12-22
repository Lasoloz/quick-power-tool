package com.lasoloz.tools.qpt.gui.stage

/**
 * Stage proxy interface
 *
 * Every implementation must provide a way to show and hide stages, and also to get the state of the stage, so showing
 * and hiding can be toggled properly
 */
interface StageProxy {
    /**
     * Initialize contained stage
     */
    fun initStage()

    /**
     * Show contained stage
     */
    fun showStage()

    /**
     * Hide contained stage
     */
    fun hideStage()

    /**
     * @return Shown state of the the contained stage
     */
    fun getStageState(): StageShownState
}
