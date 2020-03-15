package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class ArticleDetailsResult : MVIResult {
    sealed class LoadArticleDetailsResult() : ArticleDetailsResult() {
        data class Success(val data: Article) : LoadArticleDetailsResult()
        object Loading : LoadArticleDetailsResult()
        data class Error(val error: Throwable) : LoadArticleDetailsResult()
    }

    sealed class RefreshArticleDetailsResult : ArticleDetailsResult() {
        data class Success(val data: Article) : RefreshArticleDetailsResult()
        object Refreshing : RefreshArticleDetailsResult()
        data class Error(val error: Throwable) : RefreshArticleDetailsResult()
    }

    sealed class SetBookmarkStatusResult : ArticleDetailsResult() {
        object Success : SetBookmarkStatusResult()
        data class Error(val error: Throwable) : SetBookmarkStatusResult()
    }


}