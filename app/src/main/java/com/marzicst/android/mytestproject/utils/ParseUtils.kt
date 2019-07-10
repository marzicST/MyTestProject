package com.marzicst.android.mytestproject.utils

import com.google.gson.JsonArray
import com.google.gson.JsonObject

object ParseUtils {

    fun getStringSafely(json: JsonObject?, name: String?, defValue: String?): String? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull) return defValue

        return json.get(name).asString
    }

    fun getStringSafely(json: JsonObject?, name: String?): String? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull) return null

        return json.get(name).asString
    }

    fun getIntSafely(json: JsonObject?, name: String?, defValue: Int?): Int? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull) return defValue

        return json.get(name).asInt
    }

    fun getFloatSafely(json: JsonObject?, name: String?, defValue: Float?): Float? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull) return defValue

        return json.get(name).asFloat
    }

    fun getLongSafely(json: JsonObject?, name: String?, defValue: Long?): Long? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull) return defValue

        return json.get(name).asLong
    }

    fun getBooleanSafely(json: JsonObject?, name: String?, defValue: Boolean?): Boolean? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull) return defValue

        return json.get(name).asBoolean
    }

    fun getDoubleSafely(json: JsonObject?, name: String?, defValue: Double?): Double? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull) return defValue

        return json.get(name).asDouble
    }

    fun getObjectSafely(json: JsonObject?, name: String?): JsonObject? {
        if (json == null || json.has(name).not() || json.get(name).isJsonObject.not() || json.get(name).isJsonNull) return null

        return json.get(name).asJsonObject
    }

    fun getArraySafely(json: JsonObject?, name: String?): JsonArray? {
        if (json == null || json.has(name).not() || json.get(name).isJsonNull || json.get(name).isJsonArray.not()) return null

        return json.get(name).asJsonArray
    }
}