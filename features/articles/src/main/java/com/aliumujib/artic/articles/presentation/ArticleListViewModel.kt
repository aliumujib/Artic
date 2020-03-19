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
            }.onEach { result: ArticleListResult ->
                Timber.v(
                    "New results of type: ${result::class.java.canonicalName?.replace(
                        "com.aliumujib.artic.articles.presentation.ArticleListResult.",
                        ""
                    )}"
                )
            }
            .scan(ArticleListViewState.init()) { previous: ArticleListViewState, result: ArticleListResult ->
                previous.reduce(previous, result)
            }
            .distinctUntilChanged()
            .onStart { Timber.d("subscribed to states") }
            .onEach {
                Timber.v("new view state with data size: ${it.data.size}")
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
            is ArticleListIntent.LoadArticleListIntent -> ArticleListAction.LoadArticleListAction(
                page = 1
            )
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
