package com.aliumujib.artic.domain.models

import java.util.*

data class Comment(
    var id: Int,
    var name: String,
    var url: String,
    var date: Date,
    var content: String,
    var parentId: Int
)
