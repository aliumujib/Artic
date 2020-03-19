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