package com.aliumujib.artic.views.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.aliumujib.artic.views.livedata.NotNullObserver

fun <T, R> LiveData<T>.map(transformation: (T) -> R): LiveData<R> {
    return Transformations.map(this, transformation)
}


fun <T : Any, L : LiveData<T>> LifecycleOwner.nonNullObserve(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, NotNullObserver(body))
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}