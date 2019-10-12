package com.aliumujib.artic.models

import io.reactivex.Observable


interface EventsObservable<E> {
    fun events(): Observable<E>
}