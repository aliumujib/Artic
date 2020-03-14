package com.aliumujib.artic.views.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryUIModel(
    var id: Int,
    var slug: String,
    var title: String,
    var description: String,
    var parent: Int,
    var postCount: Int
):Parcelable
