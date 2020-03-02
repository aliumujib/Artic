package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.articles.presentation.ArticleListResult.*
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.views.mvi.MVIViewState


sealed class ArticleListViewState(
    val isLoading: Boolean,
    val data: List<Article>,
    val error: Throwable?
) : MVIViewState {

    data class Success(val countries: List<Article>) : ArticleListViewState(
        false,
        countries,
        null
    )

    data class Error(val throwable: Throwable) :
        ArticleListViewState(false, emptyList(), throwable)

    object Loading : ArticleListViewState(true, emptyList(), null)

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
                        result.error
                    )
                    is LoadArticleListResults.Loading -> Loading
                }
            }
            is RefreshArticleListResults -> {
                when (result) {
                    is RefreshArticleListResults.Success -> Success(
                        result.data
                    )
                    is RefreshArticleListResults.Error -> Error(result.error)
                    is RefreshArticleListResults.Refreshing -> Loading
                }
            }
        }
    }


}

