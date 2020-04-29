package com.aliumujib.artic.bookmarks.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIAction

sealed class BookmarkListAction : MVIAction {
    data class RemoveBookmarkAction(val article: Article, val isCurrentlyBookmarked: Boolean) : BookmarkListAction()
    object StreamBookmarksAction : BookmarkListAction()
}