package com.marzicst.android.mytestproject.api.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.marzicst.android.mytestproject.data.Photos
import com.marzicst.android.mytestproject.data.RaceType
import com.marzicst.android.mytestproject.utils.ParseUtils
import java.lang.reflect.Type

class RaceTypeConverter: JsonDeserializer<RaceType> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): RaceType {
        val raceType = RaceType()
        val parent = json?.asJsonObject

        raceType.id = ParseUtils.getLongSafely(parent, "race_type_id", 0)

        val edpJson = ParseUtils.getObjectSafely(parent, "edp")
        raceType.raceTypeEditionId = ParseUtils.getLongSafely(edpJson, "race_type_edition_id", 0)
        raceType.alias = ParseUtils.getStringSafely(edpJson, "alias", null)
        raceType.title = ParseUtils.getStringSafely(edpJson, "title", null)

        val photosJson = ParseUtils.getObjectSafely(parent, "photos")
        val photos: Photos? = context?.deserialize(photosJson, Photos::class.java)
        raceType.potos = photos

        return raceType
    }
}