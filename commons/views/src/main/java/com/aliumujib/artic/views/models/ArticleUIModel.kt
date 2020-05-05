/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aliumujib.artic.views.models

import android.os.Parcelable
import android.text.Spanned
import android.text.format.DateUtils
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.*
import com.aliumujib.artic.views.ext.fromHtml

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
    var isBookmarked: Boolean
) : Parcelable {

    @IgnoredOnParcel
    val dateString: String = DateUtils.getRelativeTimeSpanString(date.time, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS).toString()

    @IgnoredOnParcel
    val titleHtml: Spanned = fromHtml(title)

}
