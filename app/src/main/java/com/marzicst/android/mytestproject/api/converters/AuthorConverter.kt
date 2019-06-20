package com.marzicst.android.mytestproject.api.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.marzicst.android.mytestproject.data.Author
import com.marzicst.android.mytestproject.data.Photos
import com.marzicst.android.mytestproject.utils.ParseUtils
import java.lang.reflect.Type

class AuthorConverter: JsonDeserializer<Author> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Author {
        val author = Author()
        val parent = json?.asJsonObject

        author.id = ParseUtils.getLongSafely(parent, "author_id", 0)
        val edpJson = ParseUtils.getObjectSafely(parent, "edp")
        author.authorEditionId = ParseUtils.getLongSafely(edpJson, "author_edition_id", 0)
        author.authorId = ParseUtils.getStringSafely(edpJson, "author_id", null)
        author.firstName = ParseUtils.getStringSafely(edpJson, "first_name", null)
        author.lastName = ParseUtils.getStringSafely(edpJson, "last_name", null)

        val photosJson = ParseUtils.getObjectSafely(parent, "avatar")
        val photos: Photos? = context?.deserialize(photosJson, Photos::class.java)
        author.avatar = photos

        return author
    }
}