package com.aliumujib.artic.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val applicationClass: Application) {

    @Provides
    fun providesContext(): Context = applicationClass

}