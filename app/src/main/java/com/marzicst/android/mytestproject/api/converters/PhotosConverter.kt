package com.marzicst.android.mytestproject.api.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.marzicst.android.mytestproject.data.Photos
import com.marzicst.android.mytestproject.utils.ParseUtils
import java.lang.reflect.Type

class PhotosConverter: JsonDeserializer<Photos> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Photos {
        val photos = Photos()
        val parent = json?.asJsonObject

        photos.s1 = getConvertedUrl(parent, "s1")
        photos.s2 = getConvertedUrl(parent, "s2")
        photos.s3 = getConvertedUrl(parent, "s3")
        photos.s4 = getConvertedUrl(parent, "s4")
        photos.s5 = getConvertedUrl(parent, "s5")
        photos.s6 = getConvertedUrl(parent, "s6")
        photos.s7 = getConvertedUrl(parent, "s7")

        return photos
    }

    private fun getConvertedUrl(json: JsonObject?, name: String) : String? {
        var url = ParseUtils.getStringSafely(json, name, null)

        url = url?.replace("%s", "1")
        if (url?.substring(0, 4) != "http") url = "https:$url"

        return url
    }
}