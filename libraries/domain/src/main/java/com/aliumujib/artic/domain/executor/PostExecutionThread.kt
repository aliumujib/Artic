package com.aliumujib.artic.domain.executor

import kotlinx.coroutines.CoroutineDispatcher


interface PostExecutionThread {

    val ui: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher
}
