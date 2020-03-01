package com.aliumujib.artic.domain.repositories.articles

import com.aliumujib.artic.domain.models.Article
import kotlinx.coroutines.flow.Flow


interface IArticlesRepository {

    fun getArticles(page: Int, isInternetAvailable: Boolean = true): Flow<List<Article>>

    suspend fun bookmarkArticle(article: Article)

    suspend fun unBookmarkArticle(articleId: Int)

    fun getBookmarkedArticles(): Flow<List<Article>>

    fun searchArticles(query: String, page: Int): Flow<List<Article>>

}