package com.aliumujib.artic.cache.models

import java.util.*

data class CommentCacheModel(
    var id: Int,
    var name: String,
    var url: String,
    var date: Date,
    var content: String,
    var parentId: Int
)
