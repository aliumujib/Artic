package com.aliumujib.artic.data.repositories.articles.cache

import com.aliumujib.artic.data.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface IArticlesCache  {

    suspend fun clearArticles()

    suspend fun saveArticles(articles: List<ArticleEntity>)

    fun getArticles(): Flow<List<ArticleEntity>>

    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    suspend  fun setArticleAsBookmarked(articleId: Int)

    suspend  fun setArticleAsNotBookmarked(articleId: Int)

    suspend  fun areArticlesCached(): Boolean

    suspend fun setLastCacheTime(lastCache: Long)

    suspend fun isArticlesCacheExpired(): Boolean
}