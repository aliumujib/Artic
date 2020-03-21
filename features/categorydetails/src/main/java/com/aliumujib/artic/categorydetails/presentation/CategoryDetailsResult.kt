package com.aliumujib.artic.categorydetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class CategoryDetailsResult : MVIResult {

    sealed class LoadCategoryArticlesResults() : CategoryDetailsResult() {
        data class Success(val data: List<Article>, val isGrid: Boolean) : LoadCategoryArticlesResults()
        object Loading : LoadCategoryArticlesResults()
        data class Error(val error: Throwable) : LoadCategoryArticlesResults()
    }

    sealed class FetchMoreArticlesForCategoryResults() : CategoryDetailsResult() {
        data class Success(val data: List<Article>) : FetchMoreArticlesForCategoryResults()
        object Loading : FetchMoreArticlesForCategoryResults()
        data class Error(val error: Throwable) : FetchMoreArticlesForCategoryResults()
    }

    sealed class SetBookmarkStatusResults : CategoryDetailsResult() {
        data class Success(var article: Article) : SetBookmarkStatusResults()
        data class Error(val error: Throwable) : SetBookmarkStatusResults()
    }

}