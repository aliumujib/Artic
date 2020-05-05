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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, T> constructor(
        private val postExecutionThread: PostExecutionThread
) {

    /**
     * Function which builds Flow instance based on given arguments
     * @param params initial use case arguments
     */
    abstract fun build(params: Params? = null): Flow<T>

    protected fun execute(params: Params? = null): Flow<T> {
         return this.build(params)
            .flowOn(postExecutionThread.io)
    }


}
