package com.aliumujib.artic.cache.impl

import com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper
import com.aliumujib.artic.cache.room.ArticlesDao
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject

class IArticlesCacheImpl @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val articleCacheModelMapper: ArticleCacheModelMapper,
    private val cacheTimeManager: CacheTimeManager
) : IArticlesCache {

    override fun clearArticles(): Completable {
        return articlesDao.deleteAllArticles()
    }

    override fun saveArticles(articles: List<ArticleEntity>) {
        articlesDao.insert(articleCacheModelMapper.mapToModelList(articles))
    }

    override fun getArticles(): Flowable<List<ArticleEntity>> {
        return articlesDao.getAllCachedArticles().map {
            articleCacheModelMapper.mapToEntityList(it)
        }
    }

    override fun getBookmarkedArticles(): Flowable<List<ArticleEntity>> {
        return articlesDao.getAllBookmarkedArticles().map {
            articleCacheModelMapper.mapToEntityList(it)
        }
    }

    override fun setArticleAsBookmarked(article: ArticleEntity): Completable {
        return Completable.defer {
            articlesDao.insert(articleCacheModelMapper.mapToModel(article))
            Completable.complete()
        }
    }

    override fun setArticleAsNotBookmarked(articleId: Int): Completable {
        return Completable.defer {
            articlesDao.unBookmarkArticle(articleId)
            Completable.complete()
        }
    }

    override fun areArticlesCached(): Boolean {
        return articlesDao.getAllCachedArticlesCount() > 0
    }

    override fun setLastCacheTime(lastCache: Long) {
        cacheTimeManager.saveLastCacheTime(lastCache)
    }

    override fun isArticlesCacheExpired(): Boolean {
        val now = Calendar.getInstance().time.time
        val lastCache = cacheTimeManager.getLastCacheTime()
        val oneDay = 60 * 60 * 24
        return (now - lastCache) > oneDay
    }
}