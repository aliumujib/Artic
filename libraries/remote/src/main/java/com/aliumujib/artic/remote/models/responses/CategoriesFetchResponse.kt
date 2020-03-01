package com.aliumujib.artic.remote.models.responses

import com.aliumujib.artic.remote.models.Category

data class CategoriesFetchResponse(
    val categories: List<Category>,
    val count: Int,
    val status: String
)
