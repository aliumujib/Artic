package com.aliumujib.artic.categories.presentation

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.views.ext.holdOn
import com.aliumujib.artic.views.mvi.MVIViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class CategoryListViewModel(
    private val categoryListActionProcessor: CategoryListActionProcessor
) : ViewModel(), MVIViewModel<CategoryListIntent, CategoryListViewState> {

    private var _actionBroadcastChannel = ConflatedBroadcastChannel<CategoryListAction>()
    private var actionsFlow = _actionBroadcastChannel.asFlow()

    private var _statesBroadcastChannel = ConflatedBroadcastChannel<CategoryListViewState>()
    private var statesFlow = _statesBroadcastChannel.asFlow()


    fun processActions() {

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
                _statesBroadcastChannel.offer(it)
            }.launchIn(viewModelScope)
    }


    override fun states(): Flow<CategoryListViewState> {
        return statesFlow
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
