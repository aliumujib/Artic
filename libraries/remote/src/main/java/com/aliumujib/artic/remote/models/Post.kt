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
