package com.marzicst.android.mytestproject.api.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.marzicst.android.mytestproject.data.Photos
import com.marzicst.android.mytestproject.utils.ParseUtils
import java.lang.reflect.Type

class PhotosConverter: JsonDeserializer<Photos> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Photos {
        val photos = Photos()
        val parent = json?.asJsonObject

        photos.s1 = ParseUtils.getStringSafely(parent, "s1", null)
        photos.s2 = ParseUtils.getStringSafely(parent, "s2", null)
        photos.s3 = ParseUtils.getStringSafely(parent, "s3", null)
        photos.s4 = ParseUtils.getStringSafely(parent, "s4", null)
        photos.s5 = ParseUtils.getStringSafely(parent, "s5", null)
        photos.s6 = ParseUtils.getStringSafely(parent, "s6", null)
        photos.s7 = ParseUtils.getStringSafely(parent, "s7", null)

        return photos
    }
}