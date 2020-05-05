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
package com.aliumujib.artic.data.repositories.articles

import com.aliumujib.artic.data.mapper.ArticleEntityMapper
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.repositories.articles.cache.IArticlesCache
import com.aliumujib.artic.data.repositories.articles.remote.IArticlesRemote
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articlesRemote: IArticlesRemote,
    private val articlesCache: IArticlesCache,
    private val articleEntityMapper: ArticleEntityMapper
) : IArticlesRepository {

    private val bookmarksMap: HashMap<Int, Boolean> = HashMap()

    override fun getArticles(refresh: Boolean, page: Int, count: Int): Flow<List<Article>> {
        return flow {
            if (articlesCache.isCacheEmpty().not() && refresh) {
                val cachedData = articlesCache.getCachedArticles().first()
                updateBookmarkMap(cachedData)
                emit(articleEntityMapper.mapFromEntityList(cachedData))
            }
            val articles: List<ArticleEntity> = articlesRemote.getArticles(page, count)
            updateArticleList(articles)
            if (page == 1 && refresh) {
                articlesCache.saveArticles(articles)
            }
            emit(articleEntityMapper.mapFromEntityList(articles))
        }
    }

    private fun updateArticleList(list: List<ArticleEntity>) {
        list.forEach {
            it.isBookmarked = bookmarksMap[it.id] ?: false
        }
    }

    private fun updateBookmarkMap(list: List<ArticleEntity>) {
        list.forEach {
            bookmarksMap[it.id] = it.isBookmarked
        }
    }

    override fun getArticleById(articleId: Int): Flow<Article> {
        return flow {
            val articles = articlesRemote.getArticle(articleId)
            emit(articleEntityMapper.mapFromEntity(articles))
        }
    }

    override fun getArticlesByCategoryId(categoryId: Int, page: Int, count: Int): Flow<List<Article>> {
        return flow {
            val articles = articlesRemote.getArticlesByCategoryId(categoryId, page, count)
            emit(articleEntityMapper.mapFromEntityList(articles))
        }
    }

    override suspend fun bookmarkArticle(article: Article): Article? {
        articlesCache.setArticleAsBookmarked(articleEntityMapper.mapToEntity(article))
        return articlesCache.findArticleById(article.id)?.let {
            articleEntityMapper.mapFromEntity(it)
        }
    }

    override suspend fun unBookmarkArticle(articleId: Int): Article? {
        articlesCache.setArticleAsNotBookmarked(articleId)
        return articlesCache.findArticleById(articleId)?.let {
            articleEntityMapper.mapFromEntity(it)
        }
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return articlesCache.getBookmarkedArticles()
            .onEach {
                updateBookmarkMap(it)
            }
            .map {
                articleEntityMapper.mapFromEntityList(it)
            }
    }

    override fun searchArticles(query: String, page: Int, count: Int): Flow<List<Article>> {
        return flow {
            val articles: List<ArticleEntity> =
                articlesRemote.searchArticles(search = query, page = page, count = count)
            emit(articleEntityMapper.mapFromEntityList(articles))
        }
    }

}
