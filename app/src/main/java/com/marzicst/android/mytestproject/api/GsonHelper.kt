package com.marzicst.android.mytestproject.api

import com.google.gson.GsonBuilder
import com.marzicst.android.mytestproject.api.converters.*
import com.marzicst.android.mytestproject.data.*

object GsonHelper {

    val instance by lazy {
        GsonBuilder()
            .registerTypeAdapter(ArticleType::class.java, ArticleTypeConverter())
            .registerTypeAdapter(Author::class.java, AuthorConverter())
            .registerTypeAdapter(News::class.java, NewsConverter())
            .registerTypeAdapter(Photos::class.java, PhotosConverter())
            .registerTypeAdapter(RaceType::class.java, RaceTypeConverter())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create()
    }
}