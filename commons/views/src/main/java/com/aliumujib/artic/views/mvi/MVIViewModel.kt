package com.aliumujib.artic.views.mvi

import kotlinx.coroutines.flow.Flow


interface MVIViewModel<I : MVIIntent, S : MVIViewState> {

    fun processIntent(intents: Flow<I>)

    fun states(): Flow<S>

}
