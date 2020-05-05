/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aliumujib.artic.articles.presentation

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.domain.usecases.settings.FetchViewModeSettings
import com.aliumujib.artic.domain.usecases.settings.UpdateViewModeSettings
import com.aliumujib.artic.views.ext.ofType
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class ArticleListActionProcessor @Inject constructor(
    private val getAllArticles: GetAllArticles,
    private val setArticleBookmarkStatus: SetArticleBookmarkStatus,
    private val updateViewModeSettings: UpdateViewModeSettings,
    private val fetchViewModeSettings: FetchViewModeSettings
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
            is ArticleListAction.SwitchArticleListViewModeAction -> {
                changeArticleListViewModeResult(flowOf(action))
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
            getAllArticles.build(GetAllArticles.Params.make(true, action.page, 5)).combine(flow { emit(fetchViewModeSettings.invoke()) })
            { list: List<Article>, isGrid: Boolean ->
                ArticleListResult.LoadArticleListResults.Success(list, isGrid)
            }.map { result ->
                result as ArticleListResult
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
            getAllArticles.build(GetAllArticles.Params.make(false, action.page, 5))
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
            getAllArticles.build(GetAllArticles.Params.make(true, 1, 5))
                .map { articles ->
                    ArticleListResult.RefreshArticleListResults.Success(data = articles) as ArticleListResult
                }
                .onStart { emit(ArticleListResult.RefreshArticleListResults.Refreshing) }
                .catch {
                    Timber.e(it)
                    emit(ArticleListResult.RefreshArticleListResults.Error(it))
                }
        }

    private fun changeArticleListViewModeResult(actionsFlow: Flow<ArticleListAction.SwitchArticleListViewModeAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge { action ->
            flow {
                emit(updateViewModeSettings.invoke(UpdateViewModeSettings.Params.make(action.isGrid)))
            }.map {
                ArticleListResult.SetArticleListViewModeResults.Success(it) as ArticleListResult
            }.catch {
                Timber.e(it)
                emit(ArticleListResult.SetBookmarkStatusResults.Error(it))
            }
        }
    }

    private fun bookmarkArticleResult(actionsFlow: Flow<ArticleListAction.SetArticleBookmarkStatusAction>): Flow<ArticleListResult> {
        return actionsFlow.flatMapMerge { action ->
            flow {
                emit(
                    setArticleBookmarkStatus.invoke(
                        SetArticleBookmarkStatus.Params.make(
                            action.article,
                            action.isBookmarked
                        )
                    )
                )
            }.map {
                ArticleListResult.SetBookmarkStatusResults.Success(it!!) as ArticleListResult
            }.catch {
                Timber.e(it)
                emit(ArticleListResult.SetBookmarkStatusResults.Error(it))
            }
        }
    }

}
