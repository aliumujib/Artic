package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.articledetails.presentation.ArticleDetailsResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


data class ArticleDetailsViewState(
    val isLoading: Boolean,
    val data: Article?,
    val error: Throwable?
) : MVIViewState {


    companion object {
        fun init(): ArticleDetailsViewState {
            return ArticleDetailsViewState(
                false,
                null,
                null
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
                    is LoadArticleDetailsResult.Success -> previousState.copy(
                        isLoading = false,
                        data = result.data,
                        error = null
                    )
                    is LoadArticleDetailsResult.Error -> previousState.copy(
                        error = result.error
                    )
                    is LoadArticleDetailsResult.Loading -> previousState.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
            is RefreshArticleDetailsResult -> {
                when (result) {
                    is RefreshArticleDetailsResult.Success -> previousState.copy(
                        isLoading = false,
                        data = result.data,
                        error = null
                    )
                    is RefreshArticleDetailsResult.Error -> previousState.copy(
                        error = result.error
                    )
                    is RefreshArticleDetailsResult.Refreshing -> previousState.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
            is SetBookmarkStatusResult -> {
                when (result) {
                    is SetBookmarkStatusResult.Success -> {
                        previousState
                    }
                    is SetBookmarkStatusResult.Error -> previousState.copy(
                        error = result.error
                    )
                }
            }
        }
    }


}

