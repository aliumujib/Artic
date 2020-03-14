package com.aliumujib.artic.views.ext

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber


@ExperimentalCoroutinesApi
fun <T> Flow<T>.holdOn(timeInMillis: Long): Flow<T> = channelFlow {
    launch {
        var lastEmission: T? = null
        var systemTimeAtStart = System.currentTimeMillis()
        var looping = true
        collect {
            while (looping) {
                lastEmission = it
                if ((System.currentTimeMillis() - systemTimeAtStart) >= timeInMillis) {
                    Timber.d("New $it before storing")
                    looping = false
                }
            }

            if (lastEmission != null) {
                Timber.d("New $lastEmission emissions before emmit")
                send(lastEmission!!)
            }
        }
    }
}



@ExperimentalCoroutinesApi
fun <U, V> Flow<U>.ofType(clazz: Class<V>): Flow<V> {
    return filter {
        clazz.isInstance(it)
    }.cast(clazz)
}

@ExperimentalCoroutinesApi
fun <U, V> Flow<U>.cast(clazz: Class<V>): Flow<V> {
    return map {
        clazz.cast(it)!!
    }
}


