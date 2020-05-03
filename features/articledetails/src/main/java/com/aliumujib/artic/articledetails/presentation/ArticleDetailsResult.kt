package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class ArticleDetailsResult : MVIResult {

    sealed class LoadArticleDetailsResult() : ArticleDetailsResult() {
        data class LoadedComments(val data: Article) : LoadArticleDetailsResult()
        data class LoadingComments(val data: Article) : LoadArticleDetailsResult()
        data class Error(val error: Throwable) : LoadArticleDetailsResult()
    }

    sealed class RefreshArticleDetailsResult : ArticleDetailsResult() {
        data class Success(val data: Article) : RefreshArticleDetailsResult()
        object Refreshing : RefreshArticleDetailsResult()
        data class Error(val error: Throwable) : RefreshArticleDetailsResult()
    }

    sealed class SetBookmarkStatusResult : ArticleDetailsResult() {
        data class Success(val data: Article) : SetBookmarkStatusResult()
        data class Error(val error: Throwable) : SetBookmarkStatusResult()
    }


}