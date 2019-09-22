package com.aliumujib.artic.cache.models

import android.arch.persistence.room.Entity
import java.util.*


@Entity
data class CommentCacheModel(
    var id: Int,
    var name: String,
    var url: String,
    var date: Date,
    var content: String,
    var parentId: Int
)
