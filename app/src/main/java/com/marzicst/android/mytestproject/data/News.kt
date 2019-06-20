package com.marzicst.android.mytestproject.data

open class News {

    var id: Long = 0
    var editionId: Long = 0
    var title: String? = null
    var alias: String? = null
    var preview: String? = null
    var publishedDate: String? = null
    var targetBlank: Boolean? = false
    var featured: Boolean? = false
    var modifiedDate: String? = null
    var videoEditionId: Long? = null
    var mediaType: String? = null
    var championshipEditionId: String? = null
    var eventEditionId: String? = null
    var creatorUserId: String? = null
    var originalEntityId: Long? = null
    var prime: Boolean? = false
    var photos: Photos? = null
    var articleType: ArticleType? = null
    var author: Author? = null
    var raceType: RaceType? = null
    var cursor: Long? = null
    var link: String? = null
}