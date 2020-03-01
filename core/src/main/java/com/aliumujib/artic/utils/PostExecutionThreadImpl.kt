package com.aliumujib.artic.utils

import com.aliumujib.artic.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PostExecutionThreadImpl @Inject constructor() : PostExecutionThread {

    override val ui: CoroutineDispatcher = Dispatchers.IO
    override val io: CoroutineDispatcher = Dispatchers.Main
    override val default: CoroutineDispatcher = Dispatchers.Default

}