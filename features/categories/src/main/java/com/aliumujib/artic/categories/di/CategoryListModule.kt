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
package com.aliumujib.artic.categories.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.aliumujib.artic.categories.presentation.CategoryListActionProcessor
import com.aliumujib.artic.categories.presentation.CategoryListViewModel
import com.aliumujib.artic.categories.ui.CategoryListFragment
import com.aliumujib.artic.categories.ui.adapter.CategoryListAdapter
import com.aliumujib.artic.di.scopes.FeatureScope
import com.aliumujib.artic.domain.usecases.categories.GetAllCategories
import com.aliumujib.artic.views.ext.viewModel
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

/**
 * Class that contributes to the object graph [CategoryListComponent].
 *
 * @see Module
 */
@Module
class CategoryListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: CategoryListFragment
) {

    /**
     * Create a provider method binding for [CategoryListViewModel].
     *
     * @param categoryListActionProcessor actionProcessor for MVI.
     * @return Instance of view model.
     * @see Provides
     */

    @ExperimentalCoroutinesApi
    @FeatureScope
    @Provides
    fun providesArticleListViewModel(
        categoryListActionProcessor: CategoryListActionProcessor
    ) = fragment.viewModel {
        CategoryListViewModel(categoryListActionProcessor)
    }

    @FeatureScope
    @Provides
    fun providesCategoryListActionProcessor(
        getAllCategories: GetAllCategories
    ) = CategoryListActionProcessor(getAllCategories)


    /**
     * Create a provider method binding for [CategoryListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCategoryListAdapter() = CategoryListAdapter(fragment)

}
