package com.aliumujib.artic.remote.models

import com.aliumujib.artic.remote.models.Thumbnail


data class ThumbnailImages(
    val full: Thumbnail?,
    val large: Thumbnail?,
    val medium: Thumbnail?,
    val medium_large: Thumbnail?,
    val post_thumbnail: Thumbnail?,
    val thumbnail: Thumbnail?
)