package com.aliumujib.artic.data.model


data class CategoryEntity(
    var id: Int,
    var slug: String,
    var title: String,
    var description: String,
    var parent: Int,
    var postCount: Int
)
