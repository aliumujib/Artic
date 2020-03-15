package com.aliumujib.artic.articledetails.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.aliumujib.artic.articledetails.details.ArticleDetailsFragment
import com.aliumujib.artic.articledetails.presentation.ArticleDetailActionProcessor
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsViewModel
import com.aliumujib.artic.articles.ui.ArticleListFragment
import com.aliumujib.artic.articles.ui.adapter.ArticleListAdapter
import com.aliumujib.artic.articles.presentation.ArticleListActionProcessor
import com.aliumujib.artic.di.scopes.FeatureScope
import com.aliumujib.artic.domain.usecases.articles.BookmarkArticle
import dagger.Module
import dagger.Provides
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.articles.GetArticleDetails
import com.aliumujib.artic.domain.usecases.articles.UnBookmarkArticle
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
        unBookmarkArticle: UnBookmarkArticle,
        bookmarkArticle: BookmarkArticle,
        getArticleDetails: GetArticleDetails
    ) = ArticleDetailActionProcessor(unBookmarkArticle, bookmarkArticle, getArticleDetails)

}
