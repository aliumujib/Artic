package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIIntent


sealed class ArticleListIntent : MVIIntent {
    data class SetArticleBookmarkStatusIntent(val article: Article, val isBookmarked :Boolean) : ArticleListIntent()
    object LoadArticleListIntent : ArticleListIntent()
    object RefreshArticleListIntent : ArticleListIntent()
    object FetchMoreArticleListIntent : ArticleListIntent()
}