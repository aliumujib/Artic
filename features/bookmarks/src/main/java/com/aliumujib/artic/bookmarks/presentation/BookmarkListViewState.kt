package com.aliumujib.artic.bookmarks.presentation

import com.aliumujib.artic.bookmarks.presentation.BookmarkListResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


data class BookmarkListViewState(
    val isLoading: Boolean = true,
    val data: List<Article> = mutableListOf(),
    val error: Throwable?,
    val isGrid: Boolean = true
) : MVIViewState {

    companion object {
        fun init(): BookmarkListViewState {
            return BookmarkListViewState(
                false,
                mutableListOf(),
                null
            )
        }
    }

    fun reduce(
        previousState: BookmarkListViewState,
        result: BookmarkListResult
    ): BookmarkListViewState {
        return when (result) {
            is StreamBookmarksResults -> {
                when (result) {
                    is StreamBookmarksResults.Success -> previousState.copy(
                        isLoading = false,
                        isGrid = result.isGrid,
                        data = result.data,
                        error = null
                    )
                    is StreamBookmarksResults.Error -> previousState.copy(
                        error = result.error
                    )
                    is StreamBookmarksResults.Loading -> previousState.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
            is RemoveBookmarkResults -> {
                when (result) {
                    is RemoveBookmarkResults.Success -> {
                        previousState
                    }
                    is RemoveBookmarkResults.Error -> {
                        previousState
                    }
                }
            }
        }
    }


}

