package com.aliumujib.artic.views.mvi

import kotlinx.coroutines.flow.Flow


interface MVIView<I : MVIIntent, in S : MVIViewState> {

    fun render(state: S)

    fun intents(): Flow<I>

}
