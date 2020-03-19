package com.aliumujib.artic.domain.threadexecutor

import kotlinx.coroutines.CoroutineDispatcher


interface PostExecutionThread {

    val ui: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher
}
