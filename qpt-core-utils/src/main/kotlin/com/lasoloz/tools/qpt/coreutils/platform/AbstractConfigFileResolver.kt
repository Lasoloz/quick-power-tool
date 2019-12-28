package com.lasoloz.tools.qpt.coreutils.platform

import java.io.File
import java.nio.file.Paths

/**
 * Abstract implementation with a saved config directory relative to platform specific locations
 *
 * @param relativeConfigDirectory Config directory
 */
abstract class AbstractConfigFileResolver(private val relativeConfigDirectory: String) : ConfigFileResolver {
    protected abstract fun getOSSpecificPath(): String

    override fun resolve(relativeFilePath: String): File {
        return Paths.get(getOSSpecificPath(), relativeConfigDirectory, relativeFilePath).toFile()
    }
}
