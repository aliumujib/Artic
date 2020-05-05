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
            getAllCategories.build(GetAllCategories.Params.make(action.page, 5))
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
