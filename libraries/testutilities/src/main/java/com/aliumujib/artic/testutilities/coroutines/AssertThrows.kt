package com.aliumujib.artic.testutilities.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.function.ThrowingRunnable

/**
 * Copy and paste coding made possible by
 *https://github.com/ReactiveCircus/streamlined/blob/master/libraries/coroutines-test-ext/src/main/java/io/github/reactivecircus/coroutines/test/ext/AssertThrows.kt
 * */

@ExperimentalCoroutinesApi
inline fun <reified T : Throwable> TestCoroutineScope.assertThrows(
    crossinline runnable: suspend () -> Unit
): T {
    val throwingRunnable = ThrowingRunnable {
        val job = async { runnable() }
        job.getCompletionExceptionOrNull()?.run { throw this }
        job.cancel()
    }
    return Assert.assertThrows(T::class.java, throwingRunnable)
}