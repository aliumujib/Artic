package com.aliumujib.artic.remote.models.responses

import com.aliumujib.artic.remote.models.Post

data class PagedPostsListResponse(
    val count: Int,
    val count_total: Int,
    val pages: Int,
    val posts: List<Post>
)
