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
