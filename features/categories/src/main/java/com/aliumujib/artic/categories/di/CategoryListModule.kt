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
