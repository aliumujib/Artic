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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.views.mvi.MVIViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import timber.log.Timber

@ExperimentalCoroutinesApi
class ArticleListViewModel(
    private val articleListActionProcessor: ArticleListActionProcessor
) : ViewModel(), MVIViewModel<ArticleListIntent, ArticleListViewState> {

    private var currentPageNumber = 1

    private var _actionBroadcastChannel = BroadcastChannel<ArticleListAction>(30)
    private var actionsFlow = _actionBroadcastChannel.asFlow()

    private var _states = MutableLiveData<ArticleListViewState>()
    private var states: LiveData<ArticleListViewState> = _states


    fun processActions() {
        actionsFlow.flatMapMerge {
                articleListActionProcessor.actionToResultTransformer(it)
            }
            .scan(ArticleListViewState.init()) { previous: ArticleListViewState, result: ArticleListResult ->
                previous.reduce(previous, result)
            }
            .distinctUntilChanged()
            .onEach {
                _states.postValue(it)
            }.launchIn(viewModelScope)
    }


    override fun states(): LiveData<ArticleListViewState> {
        return states
    }

    override fun processIntent(intents: Flow<ArticleListIntent>) {
        intents.onEach {
            Timber.v("New intent in vm: ${it::class.java.simpleName}")
            onAction(actionFromIntent(it))
        }.launchIn(viewModelScope)
    }

    private fun onAction(action: ArticleListAction) = _actionBroadcastChannel.offer(action)

    private fun actionFromIntent(intent: ArticleListIntent): ArticleListAction {
        return when (intent) {
            is ArticleListIntent.LoadArticleListIntent -> ArticleListAction.LoadArticleListAction(page = 1)
            is ArticleListIntent.SetArticleBookmarkStatusIntent -> ArticleListAction.SetArticleBookmarkStatusAction(
                intent.article,
                intent.isBookmarked
            )
            is ArticleListIntent.RefreshArticleListIntent -> ArticleListAction.RefreshArticleListAction
            ArticleListIntent.FetchMoreArticleListIntent -> {
                currentPageNumber += 1
                ArticleListAction.FetchMoreArticleListAction(currentPageNumber)
            }
           is ArticleListIntent.SwitchArticleListViewModeIntent -> {
                ArticleListAction.SwitchArticleListViewModeAction(intent.isGrid)
            }
        }
    }

}
