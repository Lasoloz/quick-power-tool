package com.lasoloz.tools.qpt.actions.util

/**
 * Exception type for informating the user about the failure of an action configuration load error
 *
 * @param message Exception message
 */
class ActionLoadException(message: String) : RuntimeException(message)
