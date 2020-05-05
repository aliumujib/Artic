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
package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.domain.models.Article
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor(
    var authorEntityMapper: AuthorEntityMapper,
    var categoryEntityMapper: CategoryEntityMapper
) : EntityMapper<ArticleEntity, Article>() {

    override fun mapFromEntity(entity: ArticleEntity): Article {
        return Article(
            entity.id,
            entity.type,
            entity.slug,
            entity.url,
            entity.status,
            entity.title,
            entity.title_plain,
            entity.content,
            entity.excerpt,
            entity.date,
            entity.modified,
            entity.thumbnail,
            entity.imageURL,
            entity.comment_count,
            categoryEntityMapper.mapFromEntityList(entity.categories),
            authorEntityMapper.mapFromEntity(entity.author),
            entity.isBookmarked
        )
    }

    override fun mapToEntity(domain: Article): ArticleEntity {
        return ArticleEntity(
            domain.id,
            domain.type,
            domain.slug,
            domain.url,
            domain.status,
            domain.title,
            domain.title_plain,
            domain.content,
            domain.excerpt,
            domain.date,
            domain.modified,
            domain.thumbnail,
            domain.fullImageURL,
            domain.comment_count,
            categoryEntityMapper.mapFromDomainList(domain.categories),
            authorEntityMapper.mapToEntity(domain.author),
            domain.isBookmarked
        )
    }

}
