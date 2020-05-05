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
package com.aliumujib.artic.articledetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.views.mvi.MVIViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*

class ArticleDetailsViewModel(private val articleDetailActionProcessor: ArticleDetailActionProcessor) :
    ViewModel(), MVIViewModel<ArticleDetailsIntent, ArticleDetailsViewState> {

    private var _actionBroadcastChannel = BroadcastChannel<ArticleDetailsAction>(30)
    private var actionsFlow = _actionBroadcastChannel.asFlow()

    private var _states = MutableLiveData<ArticleDetailsViewState>()
    private var states:LiveData<ArticleDetailsViewState> = _states


    private fun actionFromIntent(intent: ArticleDetailsIntent): ArticleDetailsAction {
        return when (intent) {
            is ArticleDetailsIntent.SetArticleBookmarkStatusIntent -> ArticleDetailsAction.BookmarkArticleAction(
                intent.article, intent.isBookmarked
            )
            is ArticleDetailsIntent.RefreshArticleDetailsIntent -> {
                ArticleDetailsAction.RefreshArticleDetailsAction(intent.article.id)
            }
            is ArticleDetailsIntent.LoadArticleDetailsIntent -> {
                ArticleDetailsAction.LoadArticleDetailsAction(intent.article)
            }
        }
    }

    fun processActions() {
        actionsFlow.flatMapMerge {
            articleDetailActionProcessor.actionToResultTransformer(it)
        }
            .scan(ArticleDetailsViewState.init()) { previous: ArticleDetailsViewState, result: ArticleDetailsResult ->
                previous.reduce(previous, result)
            }
            .distinctUntilChanged()
            .onEach {
                _states.value = it
            }.launchIn(viewModelScope)
    }

    private fun onAction(action: ArticleDetailsAction) = _actionBroadcastChannel.offer(action)

    override fun processIntent(intents: Flow<ArticleDetailsIntent>) {
        intents.onEach {
            onAction(actionFromIntent(it))
        }.launchIn(viewModelScope)
    }

    override fun states(): LiveData<ArticleDetailsViewState> {
        return states
    }


}
