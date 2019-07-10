package com.marzicst.android.mytestproject.api.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.marzicst.android.mytestproject.data.*
import com.marzicst.android.mytestproject.utils.ParseUtils
import java.lang.reflect.Type

class NewsConverter: JsonDeserializer<News> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): News {
        val news = News()
        val parent = json?.asJsonObject

        news.id = ParseUtils.getLongSafely(parent, "article_id", 0) ?:0
        val edpJson = ParseUtils.getObjectSafely(parent, "edp")
        news.editionId = ParseUtils.getLongSafely(edpJson, "article_edition_id", 0) ?:0
        news.title = ParseUtils.getStringSafely(edpJson, "title", null)
        news.alias = ParseUtils.getStringSafely(edpJson, "alias", null)
        news.preview = ParseUtils.getStringSafely(edpJson, "preview", null)
        news.publishedDate = ParseUtils.getStringSafely(edpJson, "dt_published", null)

        var data = ParseUtils.getIntSafely(edpJson, "is_target_blank", 0)
        news.targetBlank = data != 0

        data = ParseUtils.getIntSafely(edpJson, "is_featured", 0)
        news.featured = data != 0

        news.modifiedDate = ParseUtils.getStringSafely(edpJson, "dt_modified", null)
        news.videoEditionId = ParseUtils.getLongSafely(edpJson, "video_edition_id", null)
        news.mediaType = ParseUtils.getStringSafely(edpJson, "media_type", null)
        news.championshipEditionId = ParseUtils.getStringSafely(edpJson, "championship_edition_id", null)
        news.eventEditionId = ParseUtils.getStringSafely(edpJson, "event_edition_id", null)
        news.creatorUserId = ParseUtils.getStringSafely(edpJson, "creator_user_id", null)
        news.originalEntityId = ParseUtils.getLongSafely(edpJson, "original_entity_id", 0 )


        data = ParseUtils.getIntSafely(edpJson, "is_prime", 0)
        news.prime = data != 0

        val photosJson = ParseUtils.getObjectSafely(edpJson, "photos")
        val photos: Photos? = context?.deserialize(photosJson, Photos::class.java)
        news.photos = photos

        val articleTypeJson = ParseUtils.getObjectSafely(parent, "article_type")
        val articleType: ArticleType? = context?.deserialize(articleTypeJson, ArticleType::class.java)
        news.articleType = articleType

        val authorJson = ParseUtils.getObjectSafely(parent, "author")
        val author: Author? = context?.deserialize(authorJson, Author::class.java)
        news.author = author

        val raceTypeJson = ParseUtils.getObjectSafely(parent, "race_type")
        val raceType: RaceType? = context?.deserialize(raceTypeJson, RaceType::class.java)
        news.raceType = raceType

        news.cursor = ParseUtils.getLongSafely(parent, "cursor", 0)
        news.link = ParseUtils.getStringSafely(parent, "share_link", null)

        return news
    }
}