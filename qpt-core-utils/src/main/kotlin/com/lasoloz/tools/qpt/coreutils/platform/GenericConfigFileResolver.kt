package com.lasoloz.tools.qpt.coreutils.platform

/**
 * The generic configuration file resolver returns paths from the user's home directory (which can be ambiguous on
 * Windows)
 *
 * @param relativeConfigDirectory As in [AbstractConfigFileResolver]
 */
class GenericConfigFileResolver(relativeConfigDirectory: String) : AbstractConfigFileResolver(relativeConfigDirectory) {
    override fun getOSSpecificPath(): String = System.getProperty("user.home")
}
