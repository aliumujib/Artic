package com.aliumujib.artic.data.model

import java.util.*


data class CommentEntity(
    var id: Int,
    var name: String,
    var url: String,
    var date: Date,
    var content: String,
    var parentId: Int
)
