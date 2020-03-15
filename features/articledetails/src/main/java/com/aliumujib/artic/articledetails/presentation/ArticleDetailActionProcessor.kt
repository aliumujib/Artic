package com.aliumujib.artic.articledetails.presentation

import com.aliumujib.artic.domain.usecases.articles.BookmarkArticle
import com.aliumujib.artic.domain.usecases.articles.GetArticleDetails
import com.aliumujib.artic.domain.usecases.articles.UnBookmarkArticle
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class ArticleDetailActionProcessor @Inject constructor(
    private val unBookmarkArticle: UnBookmarkArticle,
    private val bookmarkArticle: BookmarkArticle,
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
            is ArticleDetailsAction.UnBookmarkArticleAction -> {
                unBookmarkArticleResult(flowOf(action))
            }
            is ArticleDetailsAction.BookmarkArticleAction -> {
                bookmarkArticleResult(flowOf(action))
            }
        }
    }


    private fun loadArticleDetailsResult(actionsFlow: Flow<ArticleDetailsAction.LoadArticleDetailsAction>): Flow<ArticleDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            getArticleDetails.build(GetArticleDetails.Params.make(action.articleId))
                .map { article ->
                    ArticleDetailsResult.LoadArticleDetailsResult.Success(data = article) as ArticleDetailsResult
                }
                .onStart { emit(ArticleDetailsResult.LoadArticleDetailsResult.Loading) }
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
                    ArticleDetailsResult.LoadArticleDetailsResult.Success(data = article) as ArticleDetailsResult
                }
                .onStart { emit(ArticleDetailsResult.RefreshArticleDetailsResult.Refreshing) }
                .catch {
                    Timber.e(it)
                    emit(ArticleDetailsResult.RefreshArticleDetailsResult.Error(it))
                }
        }

    private fun unBookmarkArticleResult(actionsFlow: Flow<ArticleDetailsAction.UnBookmarkArticleAction>): Flow<ArticleDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            flow {
                emit(unBookmarkArticle.invoke(UnBookmarkArticle.Params.make(action.articleId)))
            }.map {
                ArticleDetailsResult.UnBookmarkArticleResult.Success as ArticleDetailsResult
            }.catch {
                    Timber.e(it)
                    emit(ArticleDetailsResult.UnBookmarkArticleResult.Error(it))
                }
        }
    }

    private fun bookmarkArticleResult(actionsFlow: Flow<ArticleDetailsAction.BookmarkArticleAction>): Flow<ArticleDetailsResult> {
        return actionsFlow.flatMapMerge { action ->
            flow {
                emit(bookmarkArticle.invoke(BookmarkArticle.Params.make(action.articleId)))
            }.map {
                ArticleDetailsResult.BookmarkArticleResult.Success as ArticleDetailsResult
            }.catch {
                Timber.e(it)
                emit(ArticleDetailsResult.BookmarkArticleResult.Error(it))
            }
        }
    }

}