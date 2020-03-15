package com.aliumujib.artic.views.mvi

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


interface MVIViewModel<I : MVIIntent, S : MVIViewState> {

    fun processIntent(intents: Flow<I>)

    fun states(): LiveData<S>

}
