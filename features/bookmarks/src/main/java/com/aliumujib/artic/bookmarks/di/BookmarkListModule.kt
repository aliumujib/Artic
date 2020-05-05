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
package com.aliumujib.artic.bookmarks.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.aliumujib.artic.bookmarks.presentation.BookmarkListActionProcessor
import com.aliumujib.artic.bookmarks.presentation.BookmarkListViewModel
import com.aliumujib.artic.bookmarks.ui.BookmarksFragment
import com.aliumujib.artic.di.scopes.FeatureScope
import com.aliumujib.artic.domain.usecases.articles.GetAllBookmarkedArticles
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.domain.usecases.settings.FetchViewModeSettings
import com.aliumujib.artic.views.basearticlelist.adapter.ArticleListAdapter
import com.aliumujib.artic.views.ext.viewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [BookmarkListComponent].
 *
 * @see Module
 */
@Module
class BookmarkListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: BookmarksFragment
) {

    /**
     * Create a provider method binding for [BookmarkListViewModel].
     *
     * @param bookmarkListActionProcessor actionProcessor for MVI.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesBookmarkListViewModel(
        bookmarkListActionProcessor: BookmarkListActionProcessor
    ) = fragment.viewModel {
        BookmarkListViewModel(bookmarkListActionProcessor)
    }

    @FeatureScope
    @Provides
    fun providesBookmarkListActionProcessor(
        getAllBookmarkedArticles: GetAllBookmarkedArticles,
        setArticleBookmarkStatus: SetArticleBookmarkStatus,
        fetchViewModeSettings: FetchViewModeSettings
    ) = BookmarkListActionProcessor(
        getAllBookmarkedArticles,
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
