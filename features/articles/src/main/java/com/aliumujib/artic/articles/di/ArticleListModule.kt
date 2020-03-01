package com.aliumujib.artic.articles.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.aliumujib.artic.articles.list.ArticleListFragment
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Module
import dagger.Provides
import com.aliumujib.artic.articles.list.ArticleListViewModel
import com.aliumujib.artic.views.ext.viewModel

/**
 * Class that contributes to the object graph [ArticleListComponent].
 *
 * @see Module
 */
@Module
class ArticleListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: ArticleListFragment
) {

    /**
     * Create a provider method binding for [ArticleListViewModel].
     *
     * @param getAllArticles Use case to get all articles, paged.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesArticleListViewModel(
        getAllArticles: GetAllArticles
    ) = fragment.viewModel {
        ArticleListViewModel(getAllArticles)
    }

//    /**
//     * Create a provider method binding for [CharactersListAdapter].
//     *
//     * @return Instance of adapter.
//     * @see Provides
//     */
//    @FeatureScope
//    @Provides
//    fun providesCharactersListAdapter(
//        viewModel: CharactersListViewModel
//    ) = CharactersListAdapter(viewModel)

}
