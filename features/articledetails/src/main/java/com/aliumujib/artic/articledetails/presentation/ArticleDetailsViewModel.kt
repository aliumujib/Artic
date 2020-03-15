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
                ArticleDetailsAction.LoadArticleDetailsAction(intent.article.id)
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
                _states.postValue(it)
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
