package com.aliumujib.artic.mobile_ui.di

import android.content.Context
import com.aliumujib.artic.mobile_ui.ApplicationClass
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    /**
     * Create a provider method binding for [Context].
     *
     * @param application Sample Application.
     * @return Instance of context.
     * @see Provides
     */
    @Provides
    fun provideContext(application: ApplicationClass): Context = application.applicationContext
}
