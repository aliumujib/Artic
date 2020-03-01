package com.aliumujib.artic.settings.models

import io.reactivex.Observable


interface EventsObservable<E> {
    fun events(): Observable<E>
}