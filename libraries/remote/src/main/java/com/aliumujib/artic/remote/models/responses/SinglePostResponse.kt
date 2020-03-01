package com.aliumujib.artic.remote.models.responses

import com.aliumujib.artic.remote.models.Post

data class SinglePostResponse(
    val next_url: String,
    val post: Post,
    val previous_url: String,
    val status: String
)

