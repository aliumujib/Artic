package com.aliumujib.artic.di.modules


import com.aliumujib.artic.data.repositories.articles.ArticlesRepositoryImpl
import com.aliumujib.artic.data.repositories.categories.CategoriesRepositoryImpl
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {

    @Binds
    abstract fun bindsArticlesRepository(repository: ArticlesRepositoryImpl): IArticlesRepository

    @Binds
    abstract fun bindsCategoriesRepository(repo: CategoriesRepositoryImpl): ICategoriesRepository


}