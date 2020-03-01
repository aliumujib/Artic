package com.aliumujib.artic.di.module


import com.aliumujib.artic.data.repositories.articles.ArticlesRepoImpl
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {

    @Binds
    abstract fun bindsCharactersRepository(repo: ArticlesRepoImpl): IArticlesRepository

}