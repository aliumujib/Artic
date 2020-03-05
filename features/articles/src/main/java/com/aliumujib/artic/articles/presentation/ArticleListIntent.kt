package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.views.mvi.MVIIntent


sealed class ArticleListIntent : MVIIntent {
    data class LoadArticleListIntent(val isOnline: Boolean) : ArticleListIntent()
    data class RefreshArticleListIntent(val isOnline: Boolean) : ArticleListIntent()
    object FetchMoreArticleListIntent : ArticleListIntent()
}