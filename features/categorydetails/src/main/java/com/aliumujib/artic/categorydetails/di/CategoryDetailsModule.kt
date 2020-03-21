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
