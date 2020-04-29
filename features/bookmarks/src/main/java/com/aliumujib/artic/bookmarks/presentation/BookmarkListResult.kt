package com.aliumujib.artic.bookmarks.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class BookmarkListResult : MVIResult {

    sealed class StreamBookmarksResults : BookmarkListResult() {
        data class Success(val data: List<Article>, val isGrid: Boolean) : StreamBookmarksResults()
        object Loading : StreamBookmarksResults()
        data class Error(val error: Throwable) : StreamBookmarksResults()
    }

    sealed class RemoveBookmarkResults : BookmarkListResult() {
        data class Success(var article: Article) : RemoveBookmarkResults()
        data class Error(val error: Throwable) : RemoveBookmarkResults()
    }

}