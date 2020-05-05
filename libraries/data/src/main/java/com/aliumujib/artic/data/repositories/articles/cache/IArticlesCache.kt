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
package com.aliumujib.artic.data.repositories.articles.cache

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface IArticlesCache  {

    suspend fun clearArticles()

    suspend fun saveArticles(articles: List<ArticleEntity>)

    fun getCachedArticles(): Flow<List<ArticleEntity>>

    fun getValidCachedArticles():Flow<List<ArticleEntity>>

    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    suspend  fun setArticleAsBookmarked(article: ArticleEntity)

    suspend  fun setArticleAsNotBookmarked(articleId: Int)

    suspend  fun findArticleById(articleId: Int): ArticleEntity?

    suspend  fun areArticlesCached(): Boolean

    suspend fun setLastCacheTime(lastCache: Long)

    suspend fun isArticlesCacheExpired(): Boolean

    suspend fun isCacheEmpty(): Boolean
}
