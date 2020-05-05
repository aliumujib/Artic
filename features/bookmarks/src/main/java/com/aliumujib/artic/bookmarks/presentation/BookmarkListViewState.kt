/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
