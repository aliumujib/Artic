package com.aliumujib.artic.data.model

import android.arch.persistence.room.Entity
import java.util.*


@Entity
data class CommentEntity(
    var id: Int,
    var name: String,
    var url: String,
    var date: Date,
    var content: String,
    var parentId: Int
)
