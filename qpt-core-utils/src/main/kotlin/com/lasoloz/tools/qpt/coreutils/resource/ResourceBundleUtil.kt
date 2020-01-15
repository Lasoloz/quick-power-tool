package com.lasoloz.tools.qpt.coreutils.resource

import java.util.*

/**
 * Resource bundle utility for utf-8 resource bundle reading
 */
object ResourceBundleUtil {
    /**
     * Get resource bundle with utf-8 control
     *
     * @return Resource bundle
     */
    fun getUtf8Bundle(bundle: String): ResourceBundle = ResourceBundle.getBundle(bundle, Utf8Control())
}
