package com.aliumujib.artic.domain.models

import java.util.*

data class Article(
    var id: Int,
    var type: String,
    var slug: String,
    var url: String,
    var status: String,
    var title: String,
    var title_plain: String,
    var content: String,
    var excerpt: String,
    var date: Date,
    var modified: String,
    var thumbnail: String,
    var fullImageURL: String,
    var comment_count: Int,
    var categories: List<Category>,
    var author: Author,
    var isBookmarked: Boolean
)
