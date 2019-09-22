package com.aliumujib.artic.remote.models


data class Comment(
    val content: String,
    val date: String,
    val id: Int,
    val name: String,
    val parent: Int,
    val url: String
)