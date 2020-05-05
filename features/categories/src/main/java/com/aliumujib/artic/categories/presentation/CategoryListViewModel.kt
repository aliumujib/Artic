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
package com.aliumujib.artic.categories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.views.mvi.MVIViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import timber.log.Timber

@ExperimentalCoroutinesApi
class CategoryListViewModel(
    private val categoryListActionProcessor: CategoryListActionProcessor
) : ViewModel(), MVIViewModel<CategoryListIntent, CategoryListViewState> {

    private var _actionBroadcastChannel = ConflatedBroadcastChannel<CategoryListAction>()
    private var actionsFlow = _actionBroadcastChannel.asFlow()

    private var _states = MutableLiveData<CategoryListViewState>()
    private var states:LiveData<CategoryListViewState> = _states


    init {
        actionsFlow.flatMapMerge {
            categoryListActionProcessor.actionToResultTransformer(it)
        }
            .scan(CategoryListViewState.init()) { previous: CategoryListViewState, result: CategoryListResult ->
                previous.reduce(previous, result)
            }
            .distinctUntilChanged()
            .onStart { Timber.d("subscribed to states") }
            .onEach {
                Timber.v("new view state with data size: ${it.data.size}")
                _states.postValue(it)
            }.launchIn(viewModelScope)
    }


    override fun states(): LiveData<CategoryListViewState> {
        return states
    }

    override fun processIntent(intents: Flow<CategoryListIntent>) {
        intents.onEach {
            Timber.v("New Intent: ${it::class.java.simpleName}")
            onAction(actionFromIntent(it))
        }.launchIn(viewModelScope)
    }

    private fun onAction(action: CategoryListAction) = _actionBroadcastChannel.offer(action)

    private fun actionFromIntent(intent: CategoryListIntent): CategoryListAction {
        return when (intent) {
            is CategoryListIntent.LoadCategoriesListIntent -> {
                CategoryListAction.LoadCategoryListAction(1)
            }
        }
    }

}
