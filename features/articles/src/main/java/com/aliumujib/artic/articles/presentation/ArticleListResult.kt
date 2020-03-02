package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class ArticleListResult : MVIResult {
    sealed class LoadArticleListResults() : ArticleListResult() {
        data class Success(val data: List<Article>) : LoadArticleListResults()
        object Loading : LoadArticleListResults()
        data class Error(val error: Throwable) : LoadArticleListResults()
    }

    sealed class RefreshArticleListResults : ArticleListResult() {
        data class Success(val data: List<Article>) : RefreshArticleListResults()
        object Refreshing : RefreshArticleListResults()
        data class Error(val error: Throwable) : RefreshArticleListResults()
    }

}