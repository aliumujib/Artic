package com.aliumujib.artic.cache.models


data class CategoryCacheModel(
    var id: Int,
    var slug: String,
    var title: String,
    var description: String,
    var parent: Int,
    var postCount: Int
)
