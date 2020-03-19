package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.models.Article

sealed class ArticleListAction {
    data class SetArticleBookmarkStatusAction(val article: Article, val isBookmarked :Boolean) : ArticleListAction()
    data class LoadArticleListAction(val page: Int) : ArticleListAction()
    data class SwitchArticleListViewModeAction(val isGrid:Boolean) : ArticleListAction()
    object RefreshArticleListAction : ArticleListAction()
    data class FetchMoreArticleListAction(val page: Int) : ArticleListAction()

}