package com.aliumujib.artic.data.repositories.articles.remote

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface IArticlesRemote {

    suspend fun getArticles(page: Int): List<ArticleEntity>

    suspend fun getArticle(id: Int): ArticleEntity

    suspend fun searchArticles(search: String, page: Int): List<ArticleEntity>

   suspend fun getArticlesByCategoryId(categoryId: Int, page: Int): List<ArticleEntity>

}