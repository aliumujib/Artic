package com.aliumujib.artic.articledetails.presentation

sealed class ArticleDetailsAction {

    data class LoadArticleDetailsAction(val articleId: Int) : ArticleDetailsAction()
    data class RefreshArticleDetailsAction(val articleId: Int) : ArticleDetailsAction()
    data class BookmarkArticleAction(val articleId: Int) : ArticleDetailsAction()
    data class UnBookmarkArticleAction(val articleId: Int) : ArticleDetailsAction()

}