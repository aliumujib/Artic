package com.aliumujib.artic.domain.models

import java.util.*

data class Article(
    val id: Int,
    val type: String,
    val slug: String,
    val url: String,
    val status: String,
    val title: String,
    val title_plain: String,
    val content: String,
    val excerpt: String,
    val date: Date,
    val modified: String,
    val thumbnail: String,
    val fullImageURL: String,
    val comment_count: Int,
    val categories: List<Category>,
    val author: Author,
    val isBookmarked: Boolean
)
