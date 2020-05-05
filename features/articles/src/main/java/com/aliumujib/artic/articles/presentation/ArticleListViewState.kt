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

import com.aliumujib.artic.articles.presentation.ArticleListResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.ext.replaceItemInList
import com.aliumujib.artic.views.mvi.MVIViewState


data class ArticleListViewState(
    val isLoadingInitial: Boolean = true,
    val data: List<Article> = mutableListOf(),
    val error: Throwable?,
    val isGrid: Boolean = true,
    val isLoadingMore: Boolean = false,
    val isUpdatingCache: Boolean = false
) : MVIViewState {


    sealed class LoadingState(
        val isLoading: Boolean = true,
        val isRefreshing: Boolean,
        val isLoadingMore: Boolean
    ) {
        object InitialLoading : LoadingState(true, false, false)
        object LoadingMore : LoadingState(true, false, true)
        object Refreshing : LoadingState(true, true, false)
        object Idle : LoadingState(false, false, false)
    }

    companion object {
        fun init(): ArticleListViewState {
            return ArticleListViewState(
                false,
                mutableListOf(),
                null
            )
        }
    }


    fun reduce(
        previousState: ArticleListViewState,
        result: ArticleListResult
    ): ArticleListViewState {
        return when (result) {
            is LoadArticleListResults -> {
                when (result) {
                    is LoadArticleListResults.Success -> {
                        previousState.copy(
                            isLoadingInitial = false,
                            isLoadingMore = false,
                            isGrid = result.isGrid,
                            data = result.data,
                            error = null
                        )
                    }
                    is LoadArticleListResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingInitial = false,
                        isLoadingMore = false
                    )
                    is LoadArticleListResults.Loading -> previousState.copy(
                        isLoadingInitial = true,
                        isLoadingMore = false,
                        error = null
                    )
                }
            }
            is RefreshArticleListResults -> {
                when (result) {
                    is RefreshArticleListResults.Success -> previousState.copy(
                        isLoadingInitial = false,
                        isLoadingMore = false,
                        data = result.data,
                        error = null
                    )
                    is RefreshArticleListResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingInitial = false,
                        isLoadingMore = false
                    )
                    is RefreshArticleListResults.Refreshing -> previousState.copy(
                        isLoadingInitial = true,
                        isLoadingMore = false,
                        error = null
                    )
                }
            }
            is FetchMoreArticleListResults -> {
                when (result) {
                    is FetchMoreArticleListResults.Success -> {
                        val newData = previousState.data as MutableList
                        newData.addAll(result.data)
                        previousState.copy(
                            isLoadingInitial = false,
                            isLoadingMore = false,
                            data = newData,
                            error = null
                        )
                    }
                    is FetchMoreArticleListResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingInitial = false,
                        isLoadingMore = true
                    )
                    is FetchMoreArticleListResults.Loading -> previousState.copy(
                        isLoadingInitial = false,
                        isLoadingMore = true,
                        error = null
                    )
                }
            }
            is SetBookmarkStatusResults -> {
                when (result) {
                    is SetBookmarkStatusResults.Success -> {
                        val articles = previousState.data.replaceItemInList({
                            it.id == result.article.id
                        }, result.article)
                        val newState = previousState.copy(data = articles)
                        newState
                    }
                    is SetBookmarkStatusResults.Error -> {
                        previousState
                    }
                }
            }
            is SetArticleListViewModeResults -> {
                when (result) {
                    is SetArticleListViewModeResults.Success -> {
                        previousState.copy(isGrid = result.isGrid)
                    }
                    is SetArticleListViewModeResults.Error -> previousState
                }
            }
        }
    }


}
