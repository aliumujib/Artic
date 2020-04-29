package com.aliumujib.artic.bookmarks.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIIntent

sealed class BookmarkListIntent:MVIIntent {
    data class RemoveBookmarkIntent(val article: Article) : BookmarkListIntent()
    object StreamBookmarksIntent : BookmarkListIntent()
}