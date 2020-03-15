package com.aliumujib.artic.data.model

import java.util.*

 data class ArticleEntity(
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
    var imageURL: String,
    var comment_count: Int,
    var categories: List<CategoryEntity>,
    var author: AuthorEntity,
    var isBookmarked: Boolean
)
