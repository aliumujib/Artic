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
