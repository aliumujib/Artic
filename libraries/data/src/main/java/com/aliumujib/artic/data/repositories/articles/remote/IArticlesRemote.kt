package com.aliumujib.artic.data.repositories.articles.remote

import com.aliumujib.artic.data.model.ArticleEntity

interface IArticlesRemote {

    suspend fun getArticles(page: Int): List<ArticleEntity>

    suspend fun getArticle(id: Int): ArticleEntity

    suspend fun searchArticles(search: String, page: Int): List<ArticleEntity>

}