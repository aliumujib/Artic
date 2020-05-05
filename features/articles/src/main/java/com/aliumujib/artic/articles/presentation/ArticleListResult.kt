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
package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class ArticleListResult : MVIResult {
    sealed class LoadArticleListResults() : ArticleListResult() {
        data class Success(val data: List<Article>, val isGrid: Boolean) : LoadArticleListResults()
        object Loading : LoadArticleListResults()
        data class Error(val error: Throwable) : LoadArticleListResults()
    }

    sealed class FetchMoreArticleListResults() : ArticleListResult() {
        data class Success(val data: List<Article>) : FetchMoreArticleListResults()
        object Loading : FetchMoreArticleListResults()
        data class Error(val error: Throwable) : FetchMoreArticleListResults()
    }

    sealed class RefreshArticleListResults : ArticleListResult() {
        data class Success(val data: List<Article>) : RefreshArticleListResults()
        object Refreshing : RefreshArticleListResults()
        data class Error(val error: Throwable) : RefreshArticleListResults()
    }

    sealed class SetBookmarkStatusResults : ArticleListResult() {
        data class Success(var article: Article) : SetBookmarkStatusResults()
        data class Error(val error: Throwable) : SetBookmarkStatusResults()
    }

    sealed class SetArticleListViewModeResults : ArticleListResult() {
        data class Success(var isGrid: Boolean) : SetArticleListViewModeResults()
        data class Error(val error: Throwable) : SetArticleListViewModeResults()
    }

}
