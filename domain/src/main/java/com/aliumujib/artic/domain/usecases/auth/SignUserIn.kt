package com.aliumujib.artic.domain.usecases.auth


import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.usecases.CompletableUseCase
import com.aliumujib.artic.domain.repositories.auth.IAuthService
import io.reactivex.Completable
import javax.inject.Inject

class SignUserIn @Inject constructor(
    private val authService: IAuthService,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Nothing>(postExecutionThread) {


    public override fun buildUseCaseCompletable(params: Nothing?): Completable {
        return authService.signInUserViaGoogle()
    }


}