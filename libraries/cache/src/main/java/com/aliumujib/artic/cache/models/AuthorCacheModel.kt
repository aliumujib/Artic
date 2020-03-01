package com.aliumujib.artic.cache.models



data class AuthorCacheModel(
    var id: Int,
    var slug: String,
    var name: String,
    var first_name: String,
    var last_name: String,
    var nickname: String,
    var url: String,
    var description: String
)
