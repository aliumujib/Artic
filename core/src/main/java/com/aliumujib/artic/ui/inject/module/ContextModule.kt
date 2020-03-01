package com.aliumujib.artic.ui.inject.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val applicationClass: Application) {

    @Provides
    fun providesContext(): Context = applicationClass

}