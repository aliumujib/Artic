package com.aliumujib.artic.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ARTICLES")
data class ArticleCacheModel(
    @PrimaryKey
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
    var isBookmarked: Boolean
)
