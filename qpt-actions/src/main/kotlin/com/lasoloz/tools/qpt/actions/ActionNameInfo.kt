package com.lasoloz.tools.qpt.actions

/**
 * Additional action information about specifier, name and description
 *
 * @param specifier Action specifier (e.g. `Open IntelliJ project`)
 * @param name Action name (e.g. `my-custom-project`)
 * @param description Description of the action (e.g. `Located at "/home/user/proj/myproj"`)
 */
data class ActionNameInfo(val specifier: String, val name: String, val description: String)
