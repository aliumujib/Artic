package com.aliumujib.artic.categories.presentation

import com.aliumujib.artic.categories.presentation.CategoryListResult.LoadCategoryListResults
import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.views.mvi.MVIViewState


data class CategoryListViewState(
    val isLoading: Boolean,
    val data: List<Category> = mutableListOf(),
    val error: Throwable?
) : MVIViewState {


    companion object {
        fun init(): CategoryListViewState {
            return CategoryListViewState(
                false,
                mutableListOf(),
                null
            )
        }
    }


    fun reduce(
        previousState: CategoryListViewState,
        result: CategoryListResult
    ): CategoryListViewState {
        return when (result) {
            is LoadCategoryListResults.Success -> previousState.copy(
                isLoading = false,
                data = result.data,
                error = null
            )
            is LoadCategoryListResults.Error -> previousState.copy(error = result.error)
            is LoadCategoryListResults.Loading -> previousState.copy(isLoading = true, error = null)
        }
    }


}

