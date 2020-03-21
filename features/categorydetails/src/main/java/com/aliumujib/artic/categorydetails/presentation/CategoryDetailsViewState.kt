package com.aliumujib.artic.categorydetails.presentation

import com.aliumujib.artic.categorydetails.presentation.CategoryDetailsResult.*
import com.aliumujib.artic.domain.models.Article
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
                        val articles = previousState.data.toMutableList() //makes a new copy of the array
                        (articles).find { it.id == result.article.id }?.isBookmarked =
                            result.article.isBookmarked //we then change the property of the list that we need to.
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

