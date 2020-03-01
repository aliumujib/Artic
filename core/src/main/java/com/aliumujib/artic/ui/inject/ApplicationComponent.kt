package com.aliumujib.artic.ui.inject


import com.aliumujib.artic.ApplicationClass
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.ui.inject.module.ApplicationModule
import com.aliumujib.artic.ui.inject.module.CacheModule
import com.aliumujib.artic.ui.inject.module.DataModule
import com.aliumujib.artic.ui.inject.module.RemoteModule
import com.aliumujib.artic.ui.inject.module.presentation.PresentationModule
import com.aliumujib.artic.ui.inject.module.ui.UIModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ApplicationModule::class, UIModule::class,
        PresentationModule::class, RemoteModule::class, DataModule::class, CacheModule::class]
)
interface ApplicationComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ApplicationClass): Builder

        fun build(): ApplicationComponent

    }

    fun articlesRepository(): IArticlesRepository

    fun inject(application: ApplicationClass)

}