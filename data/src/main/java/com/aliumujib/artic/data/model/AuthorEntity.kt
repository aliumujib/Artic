package com.aliumujib.artic.data.model

import android.arch.persistence.room.Entity


@Entity
data class AuthorEntity(
    var id: Int,
    var slug: String,
    var name: String,
    var first_name: String,
    var last_name: String,
    var nickname: String,
    var url: String,
    var description: String
)
