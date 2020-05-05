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
package com.aliumujib.artic.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ARTICLES")
data class ArticleCacheModel(
    @PrimaryKey
    var id: Int,
    var type: String,
    var slug: String,
    var url: String,
    var status: String,
    var title: String,
    var title_plain: String,
    var content: String,
    var excerpt: String,
    var date: Long,
    var modified: String,
    var thumbnail: String,
    var imageURL: String,
    var comment_count: Int,
    var categories: List<CategoryCacheModel>,
    var author: AuthorCacheModel,
    var isBookmarked: Boolean
)
