package com.aliumujib.artic.categories.presentation

import com.aliumujib.artic.domain.models.Category
import com.aliumujib.artic.views.mvi.MVIResult


sealed class CategoryListResult : MVIResult {

    sealed class LoadCategoryListResults : CategoryListResult() {
        data class Success(val data: List<Category>) : LoadCategoryListResults()
        object Loading : LoadCategoryListResults()
        data class Error(val error: Throwable) : LoadCategoryListResults()
    }

}