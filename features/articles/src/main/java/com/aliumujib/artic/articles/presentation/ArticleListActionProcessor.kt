package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.views.ext.ofType
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class ArticleListActionProcessor @Inject constructor(
    private val getAllArticles: GetAllArticles,
    private val setArticleBookmarkStatus: SetArticleBookmarkStatus
) {

    fun actionToResultTransformer(action: ArticleListAction): Flow<ArticleListResult> {
        return when (action) {
            is ArticleListAction.LoadArticleListAction -> {
                loadArticleListResult(flowOf(action))
            }
            is ArticleListAction.RefreshArticleListAction -> {
                pullToRefreshResult(flowOf(action))
            }
            is ArticleListAction.FetchMoreArticleListAction -> {
                loadAnotherArticleListResult(flowOf(action))
            }
            is ArticleListAction.SetArticleBookmarkStatusAction -> {
                bookmarkArticleResult(flowOf(action))
            }
        }
    }

    /**
     * For some reason, passing in a flow here produces more than one emission
     * So flowOf(A) emmits A, A or sometimes A,A,A which in turn executes more than one action.
     * This is very weird behaviour considering the fact that I'm only trying to flatMap my flow.
     * TODO find out why it behaves like that and see if the behaviour can be remedied.
     *
     * I tried writing a holdOn operator which holds all the sudden emissions for a time period and emmits only
     * the latest one, didn't make a lot of progress with that ... TODO finish writing it for the science
     * */

    fun actionToResultTransformer(actionsFlow: Flow<ArticleListAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge {
            merge(
                loadArticleListResult(actionsFlow.ofType(ArticleListAction.LoadArticleListAction::class.java)),
                pullToRefreshResult(actionsFlow.ofType(ArticleListAction.RefreshArticleListAction::class.java)),
                loadAnotherArticleListResult(actionsFlow.ofType(ArticleListAction.FetchMoreArticleListAction::class.java))
            )
        }
    }

    private fun loadArticleListResult(actionsFlow: Flow<ArticleListAction.LoadArticleListAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge { action ->
            getAllArticles.build(GetAllArticles.Params.make(true, action.page))
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
            getAllArticles.build(GetAllArticles.Params.make(false, action.page))
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
                .map { articles ->
                    ArticleListResult.RefreshArticleListResults.Success(data = articles) as ArticleListResult
                }
                .onStart { emit(ArticleListResult.RefreshArticleListResults.Refreshing) }
                .catch {
                    Timber.e(it)
                    emit(ArticleListResult.RefreshArticleListResults.Error(it))
                }
        }


    private fun bookmarkArticleResult(actionsFlow: Flow<ArticleListAction.SetArticleBookmarkStatusAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge { action ->
            flow {
                emit(setArticleBookmarkStatus.invoke(SetArticleBookmarkStatus.Params.make(action.article, action.isBookmarked)))
            }.map {
                ArticleListResult.SetBookmarkStatusResults.Success(it!!) as ArticleListResult
            }.catch {
                Timber.e(it)
                emit(ArticleListResult.SetBookmarkStatusResults.Error(it))
            }
        }
    }
}