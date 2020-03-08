package com.aliumujib.artic.articles.presentation

sealed class ArticleListAction {

    data class LoadArticleListAction(val isConnected: Boolean, val page: Int) : ArticleListAction()
    data class RefreshArticleListAction(val isConnected: Boolean) : ArticleListAction()
    data class FetchMoreArticleListAction(val page: Int) : ArticleListAction()

}