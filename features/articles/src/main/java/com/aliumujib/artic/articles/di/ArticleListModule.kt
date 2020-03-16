package com.aliumujib.artic.articles.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.aliumujib.artic.articles.ui.ArticleListFragment
import com.aliumujib.artic.articles.ui.adapter.ArticleListAdapter
import com.aliumujib.artic.articles.presentation.ArticleListActionProcessor
import com.aliumujib.artic.di.scopes.FeatureScope
import dagger.Module
import dagger.Provides
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
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
     * @param articleListActionProcessor actionProcessor for MVI.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesArticleListViewModel(
        articleListActionProcessor: ArticleListActionProcessor
    ) = fragment.viewModel {
        ArticleListViewModel(articleListActionProcessor)
    }

    @FeatureScope
    @Provides
    fun providesArticleListActionProcessor(
        getAllArticles: GetAllArticles,
        setArticleBookmarkStatus: SetArticleBookmarkStatus
    ) = ArticleListActionProcessor(getAllArticles, setArticleBookmarkStatus)



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
