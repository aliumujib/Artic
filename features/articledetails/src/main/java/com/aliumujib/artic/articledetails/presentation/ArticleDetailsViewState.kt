package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.articledetails.presentation.ArticleDetailsResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


data class ArticleDetailsViewState(
    val isLoadingArticleData: Boolean,
    val data: Article?,
    val error: Throwable?,
    val isLoadingComments: Boolean
) : MVIViewState {


    companion object {
        fun init(): ArticleDetailsViewState {
            return ArticleDetailsViewState(
                false,
                null,
                null,
                false
            )
        }
    }


    fun reduce(
        previousState: ArticleDetailsViewState,
        result: ArticleDetailsResult
    ): ArticleDetailsViewState {
        return when (result) {
            is LoadArticleDetailsResult -> {
                when (result) {
                    is LoadArticleDetailsResult.LoadedComments -> previousState.copy(
                        isLoadingArticleData = false,
                        data = result.data,
                        error = null,
                        isLoadingComments = false
                    )
                    is LoadArticleDetailsResult.Error -> previousState.copy(
                        error = result.error,
                        isLoadingArticleData = false,
                        isLoadingComments = false

                    )
                    is LoadArticleDetailsResult.LoadingComments -> previousState.copy(
                        isLoadingArticleData = false,
                        error = null,
                        data = result.data,
                        isLoadingComments = true
                    )
                }
            }
            is RefreshArticleDetailsResult -> {
                when (result) {
                    is RefreshArticleDetailsResult.Success -> previousState.copy(
                        isLoadingArticleData = false,
                        data = result.data,
                        error = null,
                        isLoadingComments = false
                    )
                    is RefreshArticleDetailsResult.Error -> previousState.copy(
                        error = result.error,
                        isLoadingArticleData = false,
                        isLoadingComments = false
                    )
                    is RefreshArticleDetailsResult.Refreshing -> previousState.copy(
                        isLoadingArticleData = true,
                        error = null,
                        isLoadingComments = true
                    )
                }
            }
            is SetBookmarkStatusResult -> {
                when (result) {
                    is SetBookmarkStatusResult.Success -> {
                        previousState.copy(data = result.data)
                    }
                    is SetBookmarkStatusResult.Error -> previousState.copy(
                        error = result.error
                    )
                }
            }
        }
    }


}

