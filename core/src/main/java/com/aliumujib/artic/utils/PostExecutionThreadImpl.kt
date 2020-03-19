package com.aliumujib.artic.utils

import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PostExecutionThreadImpl @Inject constructor() : PostExecutionThread {

    override val ui: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default

}