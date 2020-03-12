package com.aliumujib.artic.categories.presentation

import com.aliumujib.artic.categories.presentation.CategoryListResult.*
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.categories.GetAllCategories
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class CategoryListActionProcessor @Inject constructor(
    private val getAllCategories: GetAllCategories
) {

    fun actionToResultTransformer(action: CategoryListAction): Flow<CategoryListResult> {
        return when (action) {
            is CategoryListAction.LoadCategoryListAction -> {
                loadCategoriesListResult(flowOf(action))
            }
        }
    }

    private fun loadCategoriesListResult(actionsFlow: Flow<CategoryListAction.LoadCategoryListAction>): Flow<CategoryListResult> {
        return actionsFlow.flatMapMerge { action ->
            getAllCategories.build(GetAllCategories.Params.make(action.page))
                .map { articles ->
                    LoadCategoryListResults.Success(data = articles) as CategoryListResult
                }
                .onStart { emit(LoadCategoryListResults.Loading) }
                .catch {
                    Timber.e(it)
                    emit(LoadCategoryListResults.Error(it))
                }
        }
    }
}