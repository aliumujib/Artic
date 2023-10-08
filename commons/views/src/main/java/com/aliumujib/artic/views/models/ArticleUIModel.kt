package com.aliumujib.artic.views.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ArticleUIModel(
    var id: Int,
    var type: String,
    var slug: String,
    var url: String,
    var status: String,
    var title: String,
    var title_plain: String,
    var content: String,
    var excerpt: String,
    var date: Date,
    var modified: String,
    var thumbnail: String,
    var fullImageURL: String,
    var comment_count: Int,
    var categories: List<CategoryUIModel>,
    var author: AuthorUIModel,
    var isBookmarked: Boolean = false
) : Parcelable
