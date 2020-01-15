package com.lasoloz.tools.qpt.coreutils.platform

import java.io.File

/**
 * Configuration file resolver implementable by different operating systems
 */
interface ConfigFileResolver {
    /**
     * Resolve config file on a relative file path
     *
     * @param relativeFilePath Relative file path to the configuration directory
     * @return Configuration file
     */
    fun resolve(relativeFilePath: String): File
}
