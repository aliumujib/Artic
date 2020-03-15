package com.aliumujib.artic.articledetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliumujib.artic.views.mvi.MVIViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

class ArticleDetailsViewModel(private val articleDetailActionProcessor: ArticleDetailActionProcessor) :
    ViewModel(), MVIViewModel<ArticleDetailsIntent, ArticleDetailsViewState> {

    private var _actionBroadcastChannel = BroadcastChannel<ArticleDetailsAction>(30)
    private var actionsFlow = _actionBroadcastChannel.asFlow()

    private var _statesBroadcastChannel = ConflatedBroadcastChannel<ArticleDetailsViewState>()
    private var statesFlow = _statesBroadcastChannel.asFlow()


    private fun actionFromIntent(intent: ArticleDetailsIntent): ArticleDetailsAction {
        return when (intent) {
            is ArticleDetailsIntent.BookmarkArticleIntent -> ArticleDetailsAction.BookmarkArticleAction(
                intent.article.id
            )
            is ArticleDetailsIntent.UnBookmarkArticleIntent -> ArticleDetailsAction.UnBookmarkArticleAction(
                intent.article.id
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
                _statesBroadcastChannel.offer(it)
            }.launchIn(viewModelScope)
    }

    private fun onAction(action: ArticleDetailsAction) = _actionBroadcastChannel.offer(action)

    override fun processIntent(intents: Flow<ArticleDetailsIntent>) {
        intents.onEach {
            onAction(actionFromIntent(it))
        }.launchIn(viewModelScope)
    }

    override fun states(): Flow<ArticleDetailsViewState> {
        return statesFlow
    }


}
