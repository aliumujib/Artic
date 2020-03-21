package com.aliumujib.artic.categorydetails.presentation

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
class CategoryDetailsViewModel(
    private val categoryDetailsActionProcessor: CategoryDetailsActionProcessor
) : ViewModel(), MVIViewModel<CategoryDetailsIntent, CategoryDetailsViewState> {

    private var currentPageNumber = 1

    private var _actionBroadcastChannel = BroadcastChannel<CategoryDetailsAction>(30)
    private var actionsFlow = _actionBroadcastChannel.asFlow()

    private var _states = MutableLiveData<CategoryDetailsViewState>()
    private var states: LiveData<CategoryDetailsViewState> = _states


    fun processActions() {
        actionsFlow.flatMapMerge {
            categoryDetailsActionProcessor.actionToResultTransformer(it)
        }
            .scan(CategoryDetailsViewState.init()) { previous: CategoryDetailsViewState, result: CategoryDetailsResult ->
                previous.reduce(previous, result)
            }
            .distinctUntilChanged()
            .onStart { Timber.d("subscribed to states") }
            .onEach {
                Timber.v("new view state with data size: ${it.data.size}")
                _states.postValue(it)
            }.launchIn(viewModelScope)
    }


    override fun states(): LiveData<CategoryDetailsViewState> {
        return states
    }

    override fun processIntent(intents: Flow<CategoryDetailsIntent>) {
        intents.onEach {
            Timber.v("New intent in vm: ${it::class.java.simpleName}")
            onAction(actionFromIntent(it))
        }.launchIn(viewModelScope)
    }

    private fun onAction(action: CategoryDetailsAction) = _actionBroadcastChannel.offer(action)

    private fun actionFromIntent(intent: CategoryDetailsIntent): CategoryDetailsAction {
        return when (intent) {
            is CategoryDetailsIntent.LoadCategoryArticlesIntent -> CategoryDetailsAction.LoadCategoryArticlesAction(
                page = 1,
                categoryId = intent.categoryId
            )
            is CategoryDetailsIntent.SetArticleBookmarkStatusIntent -> CategoryDetailsAction.SetArticleBookmarkStatusAction(
                intent.article,
                intent.isBookmarked
            )
            is CategoryDetailsIntent.FetchMoreArticlesForCategoryIntent -> {
                currentPageNumber += 1
                CategoryDetailsAction.FetchMoreArticlesForCategoryAction(currentPageNumber, intent.categoryId)
            }
        }
    }

}
