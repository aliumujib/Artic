package com.aliumujib.artic.ui.inject.module

import com.aliumujib.artic.BuildConfig
import com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote
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
            return WordPressServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindsArticlesRemote(remote: ArticlesRemote): IArticlesRemote

}