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
package com.aliumujib.artic.bookmarks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.bookmarks.presentation.BookmarkListAction.*
import com.aliumujib.artic.bookmarks.presentation.BookmarkListIntent.*
import com.aliumujib.artic.views.mvi.MVIViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import timber.log.Timber

@ExperimentalCoroutinesApi
class BookmarkListViewModel(
    private val bookmarkListActionProcessor: BookmarkListActionProcessor
) : ViewModel(), MVIViewModel<BookmarkListIntent, BookmarkListViewState> {

    private var currentPageNumber = 1

    private var _actionBroadcastChannel = BroadcastChannel<BookmarkListAction>(30)
    private var actionsFlow = _actionBroadcastChannel.asFlow()

    private var _states = MutableLiveData<BookmarkListViewState>()
    private var states: LiveData<BookmarkListViewState> = _states


    fun processActions() {
        actionsFlow.flatMapMerge {
            bookmarkListActionProcessor.actionToResultTransformer(it)
        }
            .scan(BookmarkListViewState.init()) { previous: BookmarkListViewState, result: BookmarkListResult ->
                previous.reduce(previous, result)
            }
            .distinctUntilChanged()
            .onStart { Timber.d("subscribed to states") }
            .onEach {
                Timber.v("new view state with data size: ${it.data.size}")
                _states.postValue(it)
            }.launchIn(viewModelScope)
    }


    override fun states(): LiveData<BookmarkListViewState> {
        return states
    }

    override fun processIntent(intents: Flow<BookmarkListIntent>) {
        intents.onEach {
            Timber.v("New intent in vm: ${it::class.java.simpleName}")
            onAction(actionFromIntent(it))
        }.launchIn(viewModelScope)
    }

    private fun onAction(action: BookmarkListAction) = _actionBroadcastChannel.offer(action)

    private fun actionFromIntent(intent: BookmarkListIntent): BookmarkListAction {
        return when (intent) {
            is StreamBookmarksIntent -> StreamBookmarksAction
            is RemoveBookmarkIntent -> RemoveBookmarkAction(
                intent.article,
                true
            )
        }
    }

}
