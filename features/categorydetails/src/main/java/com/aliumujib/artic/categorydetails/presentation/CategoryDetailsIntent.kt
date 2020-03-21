package com.aliumujib.artic.categorydetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIIntent

sealed class CategoryDetailsIntent:MVIIntent {
    data class SetArticleBookmarkStatusIntent(val article: Article, val isBookmarked :Boolean) : CategoryDetailsIntent()
    data class LoadCategoryArticlesIntent(val categoryId: Int) : CategoryDetailsIntent()
    data class FetchMoreArticlesForCategoryIntent(val categoryId: Int) : CategoryDetailsIntent()
}