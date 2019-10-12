package com.aliumujib.artic.models

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

open class ModelStore<S>(originalState: S) : Model<S> {

    /**
     *I used [PublishRelay] instead of [PublishSubject] here because:
     *
     * Subjects are useful to bridge the gap between non-Rx APIs.
     * However, they are stateful in a damaging way: when they receive an onComplete or onError they
     * no longer become usable for moving data. This is the observable contract and sometimes it is the desired behavior. Most times it is not.
     * Relays are simply Subjects without the aforementioned property.
     * They allow you to bridge non-Rx APIs into Rx easily, and without the worry of accidentally triggering a terminal state.
     * */
    private val intents = PublishRelay.create<Intent<S>>()


    private val store = intents
        .observeOn(AndroidSchedulers.mainThread())
        .scan(originalState) { oldstate, intent ->
            intent.reduce(oldstate)
        }.replay(1).apply { connect() }


    /**
     * Allows us to react to problems within the ModelStore.
     */
    private val internalDisposable = store.subscribe(::internalLogger, ::crashHandler)

    private fun internalLogger(state: S) = Timber.i("$state")

    private fun crashHandler(throwable: Throwable): Unit = throw throwable

    override fun process(intent: Intent<S>) = intents.accept(intent)

    override fun modelState(): Observable<S> = store
}