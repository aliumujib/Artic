/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
