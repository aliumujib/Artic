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
