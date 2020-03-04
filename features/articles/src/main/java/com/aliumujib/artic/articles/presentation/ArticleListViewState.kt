package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.articles.presentation.ArticleListResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


sealed class ArticleListViewState(
    val isLoading: Boolean,
    val data: List<Article>,
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
        ArticleListViewState(false, emptyList(), throwable, isLoadingMore = isLoadingMoreData)

    data class Loading(val isLoadingMoreData: Boolean) :
        ArticleListViewState(true, emptyList(), null, isLoadingMoreData)

    object Idle : ArticleListViewState(
        false,
        emptyList(),
        null
    )

    fun reduce(result: ArticleListResult): ArticleListViewState {
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
                    is FetchMoreArticleListResults.Success -> Success(
                        result.data
                    )
                    is FetchMoreArticleListResults.Error -> Error(result.error, true)
                    is FetchMoreArticleListResults.Loading -> Loading(true)
                }
            }
        }
    }


}

