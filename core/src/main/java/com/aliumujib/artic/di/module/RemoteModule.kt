package com.aliumujib.artic.di.module

import com.aliumujib.artic.BuildConfig
import com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.api.WordPressServiceFactory
import com.aliumujib.artic.remote.impl.ArticlesRemote
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): WordPressAPI {
            return WordPressServiceFactory.makeWordPressService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindsArticlesRemote(remote: ArticlesRemote): IArticlesRemote

}