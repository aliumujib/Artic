package com.aliumujib.artic.remote.models

data class Attachment(
    val caption: String,
    val description: String,
    val id: Int,
    val images: Images,
    val mime_type: String,
    val parent: Int,
    val slug: String,
    val title: String,
    val url: String
)
