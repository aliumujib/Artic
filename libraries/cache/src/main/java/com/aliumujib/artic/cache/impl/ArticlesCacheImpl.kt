package com.aliumujib.artic.cache.impl

import com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper
import com.aliumujib.artic.cache.room.ArticlesDao
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class ArticlesCacheImpl @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val articleCacheModelMapper: ArticleCacheModelMapper,
    private val cacheTimeManager: CacheTimeManager
) : IArticlesCache {

    override suspend fun clearArticles() {
        articlesDao.deleteAllArticles()
    }

    override suspend fun saveArticles(articles: List<ArticleEntity>) {
        articlesDao.insert(articleCacheModelMapper.mapToModelList(articles))
    }

    override  fun getArticles(): Flow<List<ArticleEntity>> {
        return articlesDao.getAllCachedArticles().map {
            articleCacheModelMapper.mapToEntityList(it)
        }
    }

    override  fun getBookmarkedArticles(): Flow<List<ArticleEntity>> {
        return articlesDao.getAllBookmarkedArticles().map {
            articleCacheModelMapper.mapToEntityList(it)
        }
    }

    override suspend fun setArticleAsBookmarked(article: ArticleEntity) {
        articlesDao.insert(articleCacheModelMapper.mapToModel(article))
    }

    override suspend fun setArticleAsNotBookmarked(articleId: Int) {
            articlesDao.unBookmarkArticle(articleId)
    }

    override suspend fun areArticlesCached(): Boolean {
        return articlesDao.getAllCachedArticlesCount() > 0
    }

    override suspend fun setLastCacheTime(lastCache: Long) {
        cacheTimeManager.saveLastCacheTime(lastCache)
    }

    override suspend fun isArticlesCacheExpired(): Boolean {
        val now = Calendar.getInstance().time.time
        val lastCache = cacheTimeManager.getLastCacheTime()
        val oneDay = 60 * 60 * 24
        return (now - lastCache) > oneDay
    }
}