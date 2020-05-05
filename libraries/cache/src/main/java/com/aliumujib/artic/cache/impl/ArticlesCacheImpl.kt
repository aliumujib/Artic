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
package com.aliumujib.artic.cache.impl

import com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper
import com.aliumujib.artic.cache.room.ArticlesDao
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.repositories.articles.cache.IArticlesCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

class ArticlesCacheImpl @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val articleCacheModelMapper: ArticleCacheModelMapper,
    private val cacheTimeManager: CacheTimeManager
) : IArticlesCache {

    override suspend fun clearArticles() {
        articlesDao.deleteAllArticles()
    }

    override suspend fun isCacheEmpty(): Boolean {
        return articlesDao.getAllCachedArticlesCount() == 0
    }

    override suspend fun saveArticles(articles: List<ArticleEntity>) {
        articlesDao.insert(articleCacheModelMapper.mapToModelList(articles))
        cacheTimeManager.saveLastCacheTime(Calendar.getInstance().time.time)
    }

    override fun getCachedArticles(): Flow<List<ArticleEntity>> {
        return articlesDao.getAllCachedArticlesByDate().map {
            articleCacheModelMapper.mapToEntityList(it)
        }
    }

    override fun getValidCachedArticles(): Flow<List<ArticleEntity>> {
        return articlesDao.getAllValidCachedArticles(sixHoursAgo()).map {
            articleCacheModelMapper.mapToEntityList(it)
        }
    }

    override fun getBookmarkedArticles(): Flow<List<ArticleEntity>> {
        return articlesDao.getAllBookmarkedArticles().map {
            articleCacheModelMapper.mapToEntityList(it)
        }
    }

    override suspend fun setArticleAsBookmarked(article: ArticleEntity) {
        article.isBookmarked = true
        articlesDao.insert(articleCacheModelMapper.mapToModel(article))
    }

    override suspend fun setArticleAsNotBookmarked(articleId: Int) {
        articlesDao.unBookmarkArticle(articleId)
    }

    override suspend fun findArticleById(articleId: Int): ArticleEntity? {
        return articlesDao.getArticle(articleId)?.let {
            articleCacheModelMapper.mapToEntity(it)
        }
    }

    override suspend fun areArticlesCached(): Boolean {
        return articlesDao.getAllCachedArticlesCount() > 0
    }

    override suspend fun setLastCacheTime(lastCache: Long) {
        cacheTimeManager.saveLastCacheTime(lastCache)
    }

    private fun sixHoursAgo(): Long {
        val sixHours = 60 * 60 * 6 * 1000
        return Calendar.getInstance().time.time - sixHours
    }

    override suspend fun isArticlesCacheExpired(): Boolean {
        val now = Calendar.getInstance().time.time
        val lastCache = cacheTimeManager.getLastCacheTime()
        val sixHours = 60 * 60 * 6 * 1000
        val sinceLastCache = now - lastCache
        return sinceLastCache > sixHours
    }
}
