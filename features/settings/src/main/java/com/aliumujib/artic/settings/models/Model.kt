package com.aliumujib.artic.settings.models

import io.reactivex.Observable


interface Model<S> {

    /**
     * Models will receive intents to be processed via this function
     *
     * the state of the model is immutable and processed intents will work mostly like the copy() function of kotlin objects
     * and will create a new and modified state from the old state.
     ***/
    fun process(intent: Intent<S>)


    /***
     * Observable stream of changes to the state of the model
     *
     * every time the state of the model changes, this observable will emit an event,
     * views will subscribe to this.
     */

    fun modelState(): Observable<S>

}