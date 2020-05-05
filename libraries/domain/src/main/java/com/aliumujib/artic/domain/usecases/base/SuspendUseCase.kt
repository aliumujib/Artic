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
package com.aliumujib.artic.domain.usecases.base

import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in P, R>(
    private val postExecutionThread: PostExecutionThread
) {

    suspend operator fun invoke(params: P? = null): R {
        return withContext(postExecutionThread.io) {
            execute(params)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: P?): R

}
