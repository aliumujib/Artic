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
package com.aliumujib.artic.domain.usecases.auth


import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.User
import com.aliumujib.artic.domain.repositories.auth.IAuthService
import com.aliumujib.artic.domain.usecases.base.SuspendUseCase
import javax.inject.Inject

class SignUserIn @Inject constructor(
    private val authService: IAuthService,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<Nothing, User?>(postExecutionThread) {

    override suspend fun execute(params: Nothing?): User? {
        return authService.signInUserViaGoogle()
    }

}
