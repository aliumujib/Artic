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

import com.aliumujib.artic.articledetails.presentation.ArticleDetailsResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


data class ArticleDetailsViewState(
    val isLoadingArticleData: Boolean,
    val data: Article?,
    val error: Throwable?,
    val isLoadingComments: Boolean
) : MVIViewState {


    companion object {
        fun init(): ArticleDetailsViewState {
            return ArticleDetailsViewState(
                false,
                null,
                null,
                false
            )
        }
    }


    fun reduce(
        previousState: ArticleDetailsViewState,
        result: ArticleDetailsResult
    ): ArticleDetailsViewState {
        return when (result) {
            is LoadArticleDetailsResult -> {
                when (result) {
                    is LoadArticleDetailsResult.LoadedComments -> previousState.copy(
                        isLoadingArticleData = false,
                        data = result.data,
                        error = null,
                        isLoadingComments = false
                    )
                    is LoadArticleDetailsResult.Error -> previousState.copy(
                        error = result.error,
                        isLoadingArticleData = false,
                        isLoadingComments = false

                    )
                    is LoadArticleDetailsResult.LoadingComments -> previousState.copy(
                        isLoadingArticleData = false,
                        error = null,
                        data = result.data,
                        isLoadingComments = true
                    )
                }
            }
            is RefreshArticleDetailsResult -> {
                when (result) {
                    is RefreshArticleDetailsResult.Success -> previousState.copy(
                        isLoadingArticleData = false,
                        data = result.data,
                        error = null,
                        isLoadingComments = false
                    )
                    is RefreshArticleDetailsResult.Error -> previousState.copy(
                        error = result.error,
                        isLoadingArticleData = false,
                        isLoadingComments = false
                    )
                    is RefreshArticleDetailsResult.Refreshing -> previousState.copy(
                        isLoadingArticleData = true,
                        error = null,
                        isLoadingComments = true
                    )
                }
            }
            is SetBookmarkStatusResult -> {
                when (result) {
                    is SetBookmarkStatusResult.Success -> {
                        previousState.copy(data = result.data)
                    }
                    is SetBookmarkStatusResult.Error -> previousState.copy(
                        error = result.error
                    )
                }
            }
        }
    }


}
