package com.aliumujib.artic.ui.inject.module

import android.content.Context
import com.aliumujib.artic.ApplicationClass
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {


    @Binds
    abstract fun bindsContext(applicationClass: ApplicationClass): Context



}