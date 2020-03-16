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