package com.aliumujib.artic.cache.models

import android.arch.persistence.room.Entity
import java.util.*

@Entity(tableName = "ARTICLES")
data class ArticleCacheModel(
    var id: Int,
    var type: String,
    var slug: String,
    var url: String,
    var status: String,
    var title: String,
    var title_plain: String,
    var content: String,
    var excerpt: String,
    var date: Long,
    var modified: String,
    var thumbnail: String,
    var imageURL: String,
    var comment_count: Int,
    var categories: List<CategoryCacheModel>,
    var author: AuthorCacheModel,
    var isBookmarked: Boolean = false
)
