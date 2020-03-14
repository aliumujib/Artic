package com.aliumujib.artic.articledetails.presentation

sealed class ArticleDetailsAction {

    data class LoadArticleDetailsAction(val articleId: Int) : ArticleDetailsAction()
    data class RefreshArticleListAction(val articleId: Int) : ArticleDetailsAction()

}