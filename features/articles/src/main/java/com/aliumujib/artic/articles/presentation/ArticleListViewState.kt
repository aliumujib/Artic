package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.articles.presentation.ArticleListResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


data class ArticleListViewState(
    val isLoading: Boolean,
    val data: List<Article> = mutableListOf(),
    val error: Throwable?,
    val isLoadingMore: Boolean = false,
    val isGrid: Boolean = true
) : MVIViewState {


    companion object {
        fun init(): ArticleListViewState {
            return ArticleListViewState(
                false,
                mutableListOf(),
                null
            )
        }
    }


    fun reduce(
        previousState: ArticleListViewState,
        result: ArticleListResult
    ): ArticleListViewState {
        return when (result) {
            is LoadArticleListResults -> {
                when (result) {
                    is LoadArticleListResults.Success -> previousState.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        data = result.data,
                        error = null
                    )
                    is LoadArticleListResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingMore = true
                    )
                    is LoadArticleListResults.Loading -> previousState.copy(
                        isLoading = true,
                        isLoadingMore = false,
                        error = null
                    )
                }
            }
            is RefreshArticleListResults -> {
                when (result) {
                    is RefreshArticleListResults.Success -> previousState.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        data = result.data,
                        error = null
                    )
                    is RefreshArticleListResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingMore = true
                    )
                    is RefreshArticleListResults.Refreshing -> previousState.copy(
                        isLoading = true,
                        isLoadingMore = false,
                        error = null
                    )
                }
            }
            is FetchMoreArticleListResults -> {
                when (result) {
                    is FetchMoreArticleListResults.Success -> {
                        val newData = previousState.data as MutableList
                        newData.addAll(result.data)
                        previousState.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            data = newData,
                            error = null
                        )
                    }
                    is FetchMoreArticleListResults.Error -> previousState.copy(
                        error = result.error,
                        isLoadingMore = true
                    )
                    is FetchMoreArticleListResults.Loading -> previousState.copy(
                        isLoading = true,
                        isLoadingMore = true,
                        error = null
                    )
                }
            }
            is SetBookmarkStatusResults -> {
                when (result) {
                    SetBookmarkStatusResults.Success -> {
                        previousState
                    }
                    is SetBookmarkStatusResults.Error -> {
                        previousState
                    }
                }
            }
        }
    }


}

