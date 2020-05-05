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
package com.aliumujib.artic.remote.mapper

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.remote.models.Post
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PostsMapper @Inject constructor(var categoryMapper: CategoryMapper,
                                      var authorMapper: AuthorMapper
) : RemoteModelMapper<Post, ArticleEntity> {

    override fun mapFromModel(model: Post): ArticleEntity {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: Date = safeParse(format, model.date)

        return ArticleEntity(
            model.id,
            model.type,
            model.slug,
            model.url,
            model.comment_status,
            model.title,
            model.title_plain,
            safeString(model.content),
            model.excerpt,
            date,
            model.modified,
            safeString(model.thumbnail),
            safeString(model.thumbnail_images?.full?.url),
            model.comment_count,
            categoryMapper.mapModelList(model.categories),
            authorMapper.mapFromModel(model.author),
            false
        )

    }

}
