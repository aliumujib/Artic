package com.eyowo.android.core.domain.usecases

import com.aliumujib.artic.domain.executor.PostExecutionThread
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<in Params, T> constructor(
        private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseFlowable(params: Params? = null): Flowable<T>

    open fun execute(singleObserver: DisposableSubscriber<T>, params: Params? = null) {
        val flowable = this.buildUseCaseFlowable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(flowable.subscribeWith(singleObserver))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}