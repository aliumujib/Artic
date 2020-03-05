package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.views.ext.merge
import com.aliumujib.artic.views.ext.mergeWith
import com.aliumujib.artic.views.ext.ofType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class ArticleListActionProcessor @Inject constructor(
    private val getAllArticles: GetAllArticles
) {

    fun actionToResultTransformer(actionsFlow: Flow<ArticleListAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge {
            loadArticleListResult(actionsFlow.ofType(ArticleListAction.LoadArticleListAction::class.java))
                .merge(
                    pullToRefreshResult(actionsFlow.ofType(ArticleListAction.RefreshArticleListAction::class.java)),
                    loadAnotherArticleListResult(actionsFlow.ofType(ArticleListAction.FetchMoreArticleListAction::class.java))
                )
        }
    }

    private fun loadArticleListResult(actionsFlow: Flow<ArticleListAction.LoadArticleListAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge { action ->
            getAllArticles.build(GetAllArticles.Params.make(true, action.page))
                .onEach { delay(2000) }
                .map { articles ->
                    ArticleListResult.LoadArticleListResults.Success(data = articles) as ArticleListResult
                }
                .onStart { emit(ArticleListResult.LoadArticleListResults.Loading) }
                .catch {
                    Timber.e(it)
                    emit(ArticleListResult.LoadArticleListResults.Error(it))
                }
        }
    }

    private fun loadAnotherArticleListResult(actionsFlow: Flow<ArticleListAction.FetchMoreArticleListAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge { action ->
            getAllArticles.build(GetAllArticles.Params.make(true, action.page))
                .onEach { delay(2000) }
                .map { articles ->
                    ArticleListResult.FetchMoreArticleListResults.Success(data = articles) as ArticleListResult
                }
                .onStart { emit(ArticleListResult.FetchMoreArticleListResults.Loading) }
                .catch {
                    Timber.e(it)
                    emit(ArticleListResult.FetchMoreArticleListResults.Error(it))
                }
        }
    }

    private fun pullToRefreshResult(actionsFlow: Flow<ArticleListAction.RefreshArticleListAction>): Flow<ArticleListResult> =
        actionsFlow.flatMapMerge {
            getAllArticles.build(GetAllArticles.Params.make(true, 1))
                .onEach { delay(2000) }
                .map { articles ->
                    ArticleListResult.RefreshArticleListResults.Success(data = articles) as ArticleListResult
                }
                .onStart { emit(ArticleListResult.RefreshArticleListResults.Refreshing) }
                .catch {
                    Timber.e(it)
                    emit(ArticleListResult.RefreshArticleListResults.Error(it))
                }
        }
}