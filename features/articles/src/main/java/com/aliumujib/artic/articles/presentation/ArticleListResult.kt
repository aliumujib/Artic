package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class ArticleListResult : MVIResult {
    sealed class LoadArticleListResults() : ArticleListResult() {
        data class Success(val data: List<Article>, val isGrid: Boolean) : LoadArticleListResults()
        object Loading : LoadArticleListResults()
        data class Error(val error: Throwable) : LoadArticleListResults()
    }

    sealed class FetchMoreArticleListResults() : ArticleListResult() {
        data class Success(val data: List<Article>) : FetchMoreArticleListResults()
        object Loading : FetchMoreArticleListResults()
        data class Error(val error: Throwable) : FetchMoreArticleListResults()
    }

    sealed class RefreshArticleListResults : ArticleListResult() {
        data class Success(val data: List<Article>) : RefreshArticleListResults()
        object Refreshing : RefreshArticleListResults()
        data class Error(val error: Throwable) : RefreshArticleListResults()
    }

    sealed class SetBookmarkStatusResults : ArticleListResult() {
        data class Success(var article: Article) : SetBookmarkStatusResults()
        data class Error(val error: Throwable) : SetBookmarkStatusResults()
    }

    sealed class SetArticleListViewModeResults : ArticleListResult() {
        data class Success(var isGrid: Boolean) : SetArticleListViewModeResults()
        data class Error(val error: Throwable) : SetArticleListViewModeResults()
    }

}