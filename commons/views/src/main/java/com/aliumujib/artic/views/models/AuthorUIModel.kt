package com.aliumujib.artic.views.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorUIModel(
    var id: Int,
    var slug: String,
    var name: String,
    var first_name: String,
    var last_name: String,
    var nickname: String,
    var url: String,
    var description: String
):Parcelable
