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
package com.aliumujib.artic.categorydetails.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.aliumujib.artic.articles.ui.ArticleListFragment
import com.aliumujib.artic.views.basearticlelist.adapter.ArticleListAdapter
import com.aliumujib.artic.articles.presentation.ArticleListActionProcessor
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Module
import dagger.Provides
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.categorydetails.presentation.CategoryDetailsActionProcessor
import com.aliumujib.artic.categorydetails.presentation.CategoryDetailsViewModel
import com.aliumujib.artic.categorydetails.ui.CategoryDetailsFragment
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.domain.usecases.categories.GetArticlesForCategory
import com.aliumujib.artic.domain.usecases.settings.FetchViewModeSettings
import com.aliumujib.artic.domain.usecases.settings.UpdateViewModeSettings
import com.aliumujib.artic.views.ext.viewModel

/**
 * Class that contributes to the object graph [CategoryDetailsComponent].
 *
 * @see Module
 */
@Module
class CategoryDetailsModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: CategoryDetailsFragment
) {

    /**
     * Create a provider method binding for [CategoryDetailsViewModel].
     *
     * @param categoryDetailsActionProcessor actionProcessor for MVI.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesArticleListViewModel(
        categoryDetailsActionProcessor: CategoryDetailsActionProcessor
    ) = fragment.viewModel {
        CategoryDetailsViewModel(categoryDetailsActionProcessor)
    }

    @FeatureScope
    @Provides
    fun providesCategoryDetailsActionProcessor(
        getArticlesForCategory: GetArticlesForCategory,
        setArticleBookmarkStatus: SetArticleBookmarkStatus,
        fetchViewModeSettings: FetchViewModeSettings
    ) = CategoryDetailsActionProcessor(
        getArticlesForCategory,
        setArticleBookmarkStatus,
        fetchViewModeSettings
    )


    /**
     * Create a provider method binding for [ArticleListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesArticleListAdapter() = ArticleListAdapter(fragment)

}
