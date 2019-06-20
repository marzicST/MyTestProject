package com.marzicst.android.mytestproject.api.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.marzicst.android.mytestproject.data.ArticleType
import com.marzicst.android.mytestproject.utils.ParseUtils
import java.lang.reflect.Type

class ArticleTypeConverter: JsonDeserializer<ArticleType> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ArticleType {
        val articleType = ArticleType()
        val parent = json?.asJsonObject

        articleType.id = ParseUtils.getLongSafely(parent, "article_type_id", 0)
        val edpJson = ParseUtils.getObjectSafely(parent, "edp")
        articleType.articleTypeEditionId = ParseUtils.getLongSafely(edpJson, "article_type_edition_id", 0)
        articleType.title = ParseUtils.getStringSafely(edpJson, "title", null)

        return articleType
    }
}