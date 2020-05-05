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
package com.aliumujib.artic.cache.models.mappers

import com.aliumujib.artic.cache.models.ArticleCacheModel
import com.aliumujib.artic.data.model.ArticleEntity
import java.util.*
import javax.inject.Inject

class ArticleCacheModelMapper @Inject constructor(
    private val authorCacheModelMapper: AuthorCacheModelMapper,
    private val categoryCacheModelMapper: CategoryCacheModelMapper
) :
    CacheModelMapper<ArticleCacheModel, ArticleEntity> {

    override fun mapToEntity(model: ArticleCacheModel): ArticleEntity {
        return ArticleEntity(
            model.id,
            model.type,
            model.slug,
            model.imageURL,
            model.status,
            model.title,
            model.title_plain,
            model.content,
            model.excerpt,
            Date(model.date),
            model.modified,
            model.thumbnail,
            model.imageURL,
            model.comment_count,
            categoryCacheModelMapper.mapToEntityList(model.categories),
            authorCacheModelMapper.mapToEntity(model.author),
            model.isBookmarked
        )
    }

    override fun mapToModel(entity: ArticleEntity): ArticleCacheModel {
        return ArticleCacheModel(
            entity.id,
            entity.type,
            entity.slug,
            entity.imageURL,
            entity.status,
            entity.title,
            entity.title_plain,
            entity.content,
            entity.excerpt,
            entity.date.time,
            entity.modified,
            entity.thumbnail,
            entity.imageURL,
            entity.comment_count,
            categoryCacheModelMapper.mapToModelList(entity.categories),
            authorCacheModelMapper.mapToModel(entity.author),
            entity.isBookmarked
        )
    }
}
