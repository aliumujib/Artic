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
package com.aliumujib.artic.categorydetails.presentation

import com.aliumujib.artic.categorydetails.presentation.CategoryDetailsResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.ext.replaceItemInList
import com.aliumujib.artic.views.mvi.MVIViewState


data class CategoryDetailsViewState(
    val isLoading: Boolean = true,
    val data: List<Article> = mutableListOf(),
    val error: Throwable?,
    val isGrid: Boolean = true,
    val isLoadingMore: Boolean = false
) : MVIViewState {

    companion object {
        fun init(): CategoryDetailsViewState {
            return CategoryDetailsViewState(
                false,
                mutableListOf(),
                null
            )
        }
    }

    fun reduce(
        previousState: CategoryDetailsViewState,
        result: CategoryDetailsResult
    ): CategoryDetailsViewState {
        return when (result) {
            is LoadCategoryArticlesResults -> {
                when (result) {
                    is LoadCategoryArticlesResults.Success -> previousState.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        isGrid = result.isGrid,
                        data = result.data,
                        error = null
                    )
                    is LoadCategoryArticlesResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingMore = false
                    )
                    is LoadCategoryArticlesResults.Loading -> previousState.copy(
                        isLoading = true,
                        isLoadingMore = false,
                        error = null
                    )
                }
            }
            is FetchMoreArticlesForCategoryResults -> {
                when (result) {
                    is FetchMoreArticlesForCategoryResults.Success -> {
                        val newData = previousState.data as MutableList
                        newData.addAll(result.data)
                        previousState.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            data = newData,
                            error = null
                        )
                    }
                    is FetchMoreArticlesForCategoryResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingMore = true
                    )
                    is FetchMoreArticlesForCategoryResults.Loading -> previousState.copy(
                        isLoading = true,
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
                        }, result.article) //makes a new copy of the array
                        val newState = previousState.copy(data = articles)
                        newState
                    }
                    is SetBookmarkStatusResults.Error -> {
                        previousState
                    }
                }
            }
        }
    }


}
