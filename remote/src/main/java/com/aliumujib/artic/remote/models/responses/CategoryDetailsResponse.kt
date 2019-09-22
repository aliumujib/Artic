package com.aliumujib.artic.remote.models.responses

import com.aliumujib.artic.remote.models.Category
import com.aliumujib.artic.remote.models.Post

data class CategoryDetailsResponse(
    val category: Category,
    val count: Int,
    val pages: Int,
    val posts: List<Post>,
    val status: String
)

