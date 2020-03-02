package com.aliumujib.artic.remote.models

data class Post(
    val attachments: List<Attachment>?,
    val author: Author,
    val categories: List<Category>?,
    val comment_count: Int,
    val comment_status: String,
    val comments: List<Comment>?,
    val content: String,
    val date: String,
    val excerpt: String,
    val id: Int,
    val modified: String,
    val slug: String,
    val status: String,
    val thumbnail: String?,
    val thumbnail_images: ThumbnailImages?,
    val thumbnail_size: String,
    val title: String,
    val title_plain: String,
    val type: String,
    val url: String
)
