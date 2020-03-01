package com.aliumujib.artic.remote.models

data class Category(
    val description: String,
    val id: Int,
    val parent: Int,
    val post_count: Int,
    val slug: String,
    val title: String
)