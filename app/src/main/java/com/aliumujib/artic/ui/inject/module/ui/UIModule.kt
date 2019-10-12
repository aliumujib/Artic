package com.aliumujib.artic.ui.inject.module.ui

import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.ui.main.MainActivity
import com.aliumujib.artic.ui.main.articledetail.DetailsFragment
import com.aliumujib.artic.ui.main.articles.ArticleListFragment
import com.aliumujib.artic.ui.main.settings.editprofile.EditProfileFragment
import com.aliumujib.artic.utils.PostExecutionThreadImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UIModule {

    @Binds
    abstract fun bindPostExecutionThread(postExecutionThread: PostExecutionThreadImpl): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ArticlesListFragmentModule::class])
    abstract fun contributesArticlesFragment(): ArticleListFragment

    @ContributesAndroidInjector(modules = [ArticlesDetailsModule::class])
    abstract fun contributesDetailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    abstract fun contributesEditProfileFragment(): EditProfileFragment
}