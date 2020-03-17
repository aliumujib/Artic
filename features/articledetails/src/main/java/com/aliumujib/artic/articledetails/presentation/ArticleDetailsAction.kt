package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.domain.models.Article

sealed class ArticleDetailsAction {

    data class LoadArticleDetailsAction(val article: Article) : ArticleDetailsAction()
    data class RefreshArticleDetailsAction(val articleId: Int) : ArticleDetailsAction()
    data class BookmarkArticleAction(val article: Article, val bookmarked: Boolean) : ArticleDetailsAction()
}