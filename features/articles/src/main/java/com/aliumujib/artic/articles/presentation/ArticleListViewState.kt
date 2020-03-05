package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.articles.presentation.ArticleListResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


sealed class ArticleListViewState(
    val isLoading: Boolean,
    val data: List<Article> = mutableListOf(),
    val error: Throwable?,
    val isLoadingMore: Boolean = false,
    val isGrid: Boolean = true
) : MVIViewState {

    data class Success(val countries: List<Article>) : ArticleListViewState(
        false,
        countries,
        null
    )

    data class Error(val throwable: Throwable, val isLoadingMoreData: Boolean) :
        ArticleListViewState(false, mutableListOf(), throwable, isLoadingMore = isLoadingMoreData)

    data class Loading(val isLoadingMoreData: Boolean) :
        ArticleListViewState(true, mutableListOf(), null, isLoadingMoreData)

    object Idle : ArticleListViewState(
        false,
        mutableListOf(),
        null
    )

    fun reduce(previousState : ArticleListViewState, result: ArticleListResult): ArticleListViewState {
        return when (result) {
            is LoadArticleListResults -> {
                when (result) {
                    is LoadArticleListResults.Success -> Success(
                        result.data
                    )
                    is LoadArticleListResults.Error -> Error(
                        result.error, false
                    )
                    is LoadArticleListResults.Loading -> Loading(false)
                }
            }
            is RefreshArticleListResults -> {
                when (result) {
                    is RefreshArticleListResults.Success -> Success(
                        result.data
                    )
                    is RefreshArticleListResults.Error -> Error(result.error, false)
                    is RefreshArticleListResults.Refreshing -> Loading(false)
                }
            }
            is FetchMoreArticleListResults -> {
                when (result) {
                    is FetchMoreArticleListResults.Success -> {
                        val newData = previousState.data as MutableList
                        newData.addAll(result.data)
                        Success(newData)
                    }
                    is FetchMoreArticleListResults.Error -> Error(result.error, true)
                    is FetchMoreArticleListResults.Loading -> Loading(true)
                }
            }
        }
    }


}

