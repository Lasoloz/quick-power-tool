package com.lasoloz.tools.qpt.actions.config.json

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DatabindContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase
import com.fasterxml.jackson.databind.type.SimpleType
import com.google.inject.Inject
import com.google.inject.name.Named
import com.lasoloz.tools.qpt.actions.config.model.AbstractActionConfigModel
import com.lasoloz.tools.qpt.actions.util.ActionConstants
import com.lasoloz.tools.qpt.actions.util.ActionSaveException
import com.lasoloz.tools.qpt.injections.InjectorUtil
import kotlin.reflect.KClass

/**
 * Jackson type ID resolver for every registered action configuration subtype
 *
 * @note Action configurations are defined under the name
 * [ActionConstants.Injection.ACTION_CONFIG_TYPE_ID_TO_MODEL_NAME_KEY]
 */
class ActionConfigTypeIdResolver : TypeIdResolverBase() {
    private lateinit var typeIdsToModels: Map<String, KClass<*>>

    /**
     * Inject type IDs mapped to model classes
     *
     * @param typeIdsToModels Injected map
     */
    @Inject
    @JvmSuppressWildcards
    fun injectTypeIdsToModelsMap(
        @Named(ActionConstants.Injection.ACTION_CONFIG_TYPE_ID_TO_MODEL_NAME_KEY)
        typeIdsToModels: Map<String, KClass<out AbstractActionConfigModel>>
    ) {
        this.typeIdsToModels = typeIdsToModels
    }

    override fun init(bt: JavaType?) {
        InjectorUtil.getInjector().injectMembers(this)
        super.init(bt)
    }

    override fun idFromValue(value: Any?): String {
        if (value == null) {
            throw ActionSaveException("Cannot save `null` value")
        }
        return typeIdsToModels.entries.find { it.value == value::class }?.key
            ?: throw ActionSaveException(
                "This type cannot be serialized with this type id resolver. Maybe it wasn't registered?"
            )
    }

    override fun idFromValueAndType(value: Any?, suggestedType: Class<*>?): String {
        throw ActionSaveException("No type suggestion is allowed when type id serialization is done")
    }

    override fun getMechanism(): JsonTypeInfo.Id = JsonTypeInfo.Id.CUSTOM

    override fun typeFromId(context: DatabindContext?, id: String?): JavaType {
        if (id == null) {
            throw ActionSaveException("Cannot deserialize value with `null` type id!")
        }
        val javaType = typeIdsToModels[id]?.java
            ?: throw ActionSaveException("Cannot find class with type id `$id`. Maybe it wasn't registered?")
        return SimpleType.constructUnsafe(javaType)
    }
}
