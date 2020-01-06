package com.lasoloz.tools.qpt.actions.util

/**
 * Exception type for informing the user about the failure of an action configuration save error
 *
 * @param message Exception message
 */
class ActionSaveException(message: String) : RuntimeException(message)
