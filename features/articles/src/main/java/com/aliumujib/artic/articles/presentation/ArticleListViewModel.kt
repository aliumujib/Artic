package com.aliumujib.artic.articles.presentation

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.views.mvi.MVIViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class ArticleListViewModel(
    private val articleListActionProcessor: ArticleListActionProcessor
) : ViewModel(), MVIViewModel<ArticleListIntent, ArticleListViewState> {



    private var currentPageNumber = 1

    private var _actionBroadcastChannel = ConflatedBroadcastChannel<ArticleListAction>()
    private var actionsFlow = _actionBroadcastChannel.asFlow()


    private var _statesBroadcastChannel = ConflatedBroadcastChannel<ArticleListViewState>()
    var statesFlow = _statesBroadcastChannel.asFlow()


    fun processActions() {
            articleListActionProcessor.actionToResultTransformer(actionsFlow)
                .onEach { result: ArticleListResult ->
                    Timber.v("New results of type: ${result::class.java.simpleName}")
                }
                .scan(ArticleListViewState.Idle) { previous: ArticleListViewState, result: ArticleListResult ->
                    previous.reduce(previous,result)
                }
                .distinctUntilChanged()
                .onStart { Timber.d("subscribed to states") }
                .onEach {
                    Timber.v("new view state with data size: ${it.data.size}")
                    _statesBroadcastChannel.offer(it)
                }.launchIn(viewModelScope)
    }


    override fun states(): Flow<ArticleListViewState> {
        return statesFlow
    }

    override fun processIntent(intent: ArticleListIntent) {
        onAction(actionFromIntent(intent))
    }

    private fun onAction(action: ArticleListAction) = _actionBroadcastChannel.offer(action)

    private fun actionFromIntent(intent: ArticleListIntent): ArticleListAction {
        return when (intent) {
            is ArticleListIntent.LoadArticleListIntent -> ArticleListAction.LoadArticleListAction(
                intent.isOnline, page = 1
            )
            is ArticleListIntent.RefreshArticleListIntent -> ArticleListAction.RefreshArticleListAction(
                intent.isOnline
            )
            ArticleListIntent.FetchMoreArticleListIntent -> {
                currentPageNumber += 1
                ArticleListAction.FetchMoreArticleListAction(currentPageNumber)
            }
        }
    }


}
