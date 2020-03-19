package com.aliumujib.artic.domain.usecases.base

import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import kotlinx.coroutines.withContext

abstract class NoResultSuspendUseCase<in P>(
    private val postExecutionThread: PostExecutionThread
) {

    suspend operator fun invoke(params: P? = null) {
        return withContext(postExecutionThread.io) {
            execute(params)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: P?)

}