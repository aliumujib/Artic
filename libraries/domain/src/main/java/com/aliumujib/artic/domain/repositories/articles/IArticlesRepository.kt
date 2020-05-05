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
package com.aliumujib.artic.domain.repositories.articles

import com.aliumujib.artic.domain.models.Article
import kotlinx.coroutines.flow.Flow


interface IArticlesRepository {

    fun getArticles(refresh: Boolean, page: Int, count: Int): Flow<List<Article>>

    fun getArticleById(articleId: Int): Flow<Article>

    fun getArticlesByCategoryId(categoryId: Int, page: Int, count: Int): Flow<List<Article>>

    suspend fun bookmarkArticle(article: Article): Article?

    suspend fun unBookmarkArticle(articleId: Int): Article?

    fun getBookmarkedArticles(): Flow<List<Article>>

    fun searchArticles(query: String, page: Int, count: Int): Flow<List<Article>>

}
