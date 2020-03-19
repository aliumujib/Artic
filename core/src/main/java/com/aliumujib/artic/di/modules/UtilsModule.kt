package com.aliumujib.artic.di.modules

import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.utils.PostExecutionThreadImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UtilsModule {

    @Binds
    abstract fun bindsPostExecutionThread(postExecutionThread: PostExecutionThreadImpl): PostExecutionThread

}