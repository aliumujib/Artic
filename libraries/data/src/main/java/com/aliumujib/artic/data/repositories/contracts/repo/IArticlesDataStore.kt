package com.aliumujib.artic.data.repositories.contracts.repo

import com.aliumujib.artic.data.model.ArticleEntity
import kotlinx.coroutines.flow.Flow


interface IArticlesDataStore {

    fun getArticles(page: Int, isOnline: Boolean): Flow<List<ArticleEntity>>

    suspend fun saveArticles(projects: List<ArticleEntity>)

    suspend fun clearArticles()

    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    suspend fun setArticleAsBookmarked(articleId: Int)

    suspend fun setArticleAsNotBookmarked(articleId: Int)

}