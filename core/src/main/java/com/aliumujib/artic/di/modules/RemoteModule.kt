package com.aliumujib.artic.di.modules

import com.aliumujib.artic.BuildConfig
import com.aliumujib.artic.data.repositories.articles.remote.IArticlesRemote
import com.aliumujib.artic.data.repositories.categories.remote.ICategoriesRemote
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.api.WordPressServiceFactory
import com.aliumujib.artic.remote.impl.ArticlesRemote
import com.aliumujib.artic.remote.impl.CategoriesRemote
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

    @Binds
    abstract fun bindsCategoriesRemote(remote: CategoriesRemote): ICategoriesRemote

}