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
package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIResult


sealed class ArticleDetailsResult : MVIResult {

    sealed class LoadArticleDetailsResult() : ArticleDetailsResult() {
        data class LoadedComments(val data: Article) : LoadArticleDetailsResult()
        data class LoadingComments(val data: Article) : LoadArticleDetailsResult()
        data class Error(val error: Throwable) : LoadArticleDetailsResult()
    }

    sealed class RefreshArticleDetailsResult : ArticleDetailsResult() {
        data class Success(val data: Article) : RefreshArticleDetailsResult()
        object Refreshing : RefreshArticleDetailsResult()
        data class Error(val error: Throwable) : RefreshArticleDetailsResult()
    }

    sealed class SetBookmarkStatusResult : ArticleDetailsResult() {
        data class Success(val data: Article) : SetBookmarkStatusResult()
        data class Error(val error: Throwable) : SetBookmarkStatusResult()
    }


}
