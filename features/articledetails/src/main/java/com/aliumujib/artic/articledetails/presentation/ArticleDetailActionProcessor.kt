package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.domain.usecases.articles.GetArticleDetails
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class ArticleDetailActionProcessor @Inject constructor(
    private val setArticleBookmarkStatus: SetArticleBookmarkStatus,
    private val getArticleDetails: GetArticleDetails
) {

    fun actionToResultTransformer(action: ArticleDetailsAction): Flow<ArticleDetailsResult> {
        return when (action) {
            is ArticleDetailsAction.LoadArticleDetailsAction -> {
                loadArticleDetailsResult(flowOf(action))
            }
            is ArticleDetailsAction.RefreshArticleDetailsAction -> {
                pullToRefreshResult(flowOf(action))
            }
            is ArticleDetailsAction.BookmarkArticleAction -> {
                bookmarkArticleResult(flowOf(action))
            }
        }
    }


    private fun loadArticleDetailsResult(actionsFlow: Flow<ArticleDetailsAction.LoadArticleDetailsAction>): Flow<ArticleDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            getArticleDetails.build(GetArticleDetails.Params.make(action.article.id))
                .map { article ->
                    ArticleDetailsResult.LoadArticleDetailsResult.LoadedComments(data = article) as ArticleDetailsResult
                }
                .onStart { emit(ArticleDetailsResult.LoadArticleDetailsResult.LoadingComments(action.article)) }
                .catch {
                    Timber.e(it)
                    emit(ArticleDetailsResult.LoadArticleDetailsResult.Error(it))
                }
        }
    }

    private fun pullToRefreshResult(actionsFlow: Flow<ArticleDetailsAction.RefreshArticleDetailsAction>): Flow<ArticleDetailsResult> =
        actionsFlow.flatMapMerge { action ->
            getArticleDetails.build(GetArticleDetails.Params.make(action.articleId))
                .map { article ->
                    ArticleDetailsResult.LoadArticleDetailsResult.LoadedComments(data = article) as ArticleDetailsResult
                }
                .onStart { emit(ArticleDetailsResult.RefreshArticleDetailsResult.Refreshing) }
                .catch {
                    Timber.e(it)
                    emit(ArticleDetailsResult.RefreshArticleDetailsResult.Error(it))
                }
        }

    private fun bookmarkArticleResult(actionsFlow: Flow<ArticleDetailsAction.BookmarkArticleAction>): Flow<ArticleDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            flow {
                emit(setArticleBookmarkStatus.invoke(SetArticleBookmarkStatus.Params.make(action.article, action.bookmarked)))
            }.map {
                ArticleDetailsResult.SetBookmarkStatusResult.Success as ArticleDetailsResult
            }.catch {
                Timber.e(it)
                emit(ArticleDetailsResult.SetBookmarkStatusResult.Error(it))
            }
        }
    }

}