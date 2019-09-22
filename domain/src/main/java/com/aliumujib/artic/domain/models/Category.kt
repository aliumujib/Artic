package com.aliumujib.artic.domain.models

data class Category(
    var id: Int,
    var slug: String,
    var title: String,
    var description: String,
    var parent: Int,
    var postCount: Int
)
