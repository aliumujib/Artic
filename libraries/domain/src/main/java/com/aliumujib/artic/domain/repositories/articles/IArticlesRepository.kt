package com.aliumujib.artic.domain.repositories.articles

import com.aliumujib.artic.domain.models.Article
import kotlinx.coroutines.flow.Flow


interface IArticlesRepository {

    fun getArticles(page: Int, isInternetAvailable: Boolean = true): Flow<List<Article>>

    fun getArticleById(articleId: Int): Flow<Article>

    suspend fun bookmarkArticle(article: Int)

    suspend fun unBookmarkArticle(articleId: Int)

    fun getBookmarkedArticles(): Flow<List<Article>>

    fun searchArticles(query: String, page: Int): Flow<List<Article>>

}