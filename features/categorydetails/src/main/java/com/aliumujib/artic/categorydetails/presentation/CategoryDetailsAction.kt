package com.aliumujib.artic.categorydetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIAction

sealed class CategoryDetailsAction : MVIAction {
    data class SetArticleBookmarkStatusAction(val article: Article, val isBookmarked: Boolean) : CategoryDetailsAction()
    data class LoadCategoryArticlesAction(val page: Int, val categoryId: Int) : CategoryDetailsAction()
    data class FetchMoreArticlesForCategoryAction(val page: Int, val categoryId: Int) : CategoryDetailsAction()
}