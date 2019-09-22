package com.aliumujib.artic.remote.models

data class Images(
    val full: Thumbnail?,
    val large: Thumbnail?,
    val medium: Thumbnail?,
    val medium_large: Thumbnail?,
    val post_thumbnail: Thumbnail?,
    val thumbnail: Thumbnail?
)