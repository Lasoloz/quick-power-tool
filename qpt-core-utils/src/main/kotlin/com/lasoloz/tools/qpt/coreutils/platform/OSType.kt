package com.lasoloz.tools.qpt.coreutils.platform

/**
 * Operating system type
 */
enum class OSType {
    /**
     * UNIX like (UNIX, GNU/Linux, etc.)
     */
    UNIX_LIKE,

    /**
     * Windows
     */
    WINDOWS;

    companion object {
        /**
         * Get operating system type by checking `os.name` system property
         */
        fun getType(): OSType = System.getProperty("os.name").let {
            if (it.startsWith("Windows")) {
                WINDOWS
            } else {
                UNIX_LIKE
            }
        }
    }
}
