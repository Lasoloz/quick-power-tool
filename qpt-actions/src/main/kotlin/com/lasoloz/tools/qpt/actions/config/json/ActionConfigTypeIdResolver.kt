package com.lasoloz.tools.qpt.actions.config.json

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DatabindContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase
import com.fasterxml.jackson.databind.type.SimpleType
import com.lasoloz.tools.qpt.actions.basic.CommandActionDTO

/**
 * WIP type id resolver for action config dtos
 */
class ActionConfigTypeIdResolver : TypeIdResolverBase() {
    // TODO: Add specific implementation!!!
    override fun idFromValue(value: Any?): String {
        return "command"
    }

    override fun idFromValueAndType(value: Any?, suggestedType: Class<*>?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMechanism(): JsonTypeInfo.Id {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun typeFromId(context: DatabindContext?, id: String?): JavaType {
        return SimpleType.constructUnsafe(CommandActionDTO::class.java)
    }
}
