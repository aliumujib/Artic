package com.aliumujib.artic.domain.repositories.articles

import com.aliumujib.artic.domain.models.Article
import kotlinx.coroutines.flow.Flow


interface IArticlesRepository {

    fun getArticles(refresh: Boolean, page: Int): Flow<List<Article>>

    fun getArticleById(articleId: Int): Flow<Article>

    suspend fun bookmarkArticle(article: Article): Article?

    suspend fun unBookmarkArticle(articleId: Int): Article?

    fun getBookmarkedArticles(): Flow<List<Article>>

    fun searchArticles(query: String, page: Int): Flow<List<Article>>

}