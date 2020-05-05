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
package com.aliumujib.artic.articledetails.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.aliumujib.artic.articledetails.details.ArticleDetailsFragment
import com.aliumujib.artic.articledetails.presentation.ArticleDetailActionProcessor
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsViewModel
import com.aliumujib.artic.di.scopes.FeatureScope
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import dagger.Module
import dagger.Provides
import com.aliumujib.artic.domain.usecases.articles.GetArticleDetails
import com.aliumujib.artic.views.ext.viewModel

/**
 * Class that contributes to the object graph [ArticleDetailsModule].
 *
 * @see Module
 */
@Module
class ArticleDetailsModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: ArticleDetailsFragment
) {

    /**
     * Create a provider method binding for [ArticleDetailsViewModel].
     *
     * @param articleDetailActionProcessor actionProcessor for MVI.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesArticleDetailsViewModel(
        articleDetailActionProcessor: ArticleDetailActionProcessor
    ) = fragment.viewModel {
        ArticleDetailsViewModel(articleDetailActionProcessor)
    }

    @FeatureScope
    @Provides
    fun providesArticleDetailActionProcessor(
        setArticleBookmarkStatus: SetArticleBookmarkStatus,
        getArticleDetails: GetArticleDetails
    ) = ArticleDetailActionProcessor(setArticleBookmarkStatus, getArticleDetails)

}
