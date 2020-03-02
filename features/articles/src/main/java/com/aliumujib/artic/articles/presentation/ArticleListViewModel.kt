package com.aliumujib.artic.articles.presentation

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
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


    private var _actionBroadcastChannel = ConflatedBroadcastChannel<ArticleListAction>()
    private var actionsFlow = _actionBroadcastChannel.asFlow()


    private var _statesBroadcastChannel = ConflatedBroadcastChannel<ArticleListViewState>()
    var statesFlow = _statesBroadcastChannel.asFlow()


    fun processActions() {
        viewModelScope.launch {
            articleListActionProcessor.actionToResultTransformer(actionsFlow)
                .onEach {result:ArticleListResult->
                    Timber.v("onResult ${result::class.java.canonicalName}")
                }
                .scan(ArticleListViewState.Idle) { state: ArticleListViewState, result: ArticleListResult ->
                    state.reduce(result)
                }
                .onStart { Timber.d("subscribed to states") }
                .collect {
                    Timber.v("onState ${it.data.size}")
                    _statesBroadcastChannel.offer(it)
                }
        }
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
            ) //TODO manage page number in viewmodel
            is ArticleListIntent.RefreshArticleListIntent -> ArticleListAction.RefreshArticleListAction(
                intent.isOnline
            )
        }
    }


}
