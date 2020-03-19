
package com.aliumujib.artic.di.components

import android.content.Context
import com.aliumujib.artic.di.modules.*
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.repositories.categories.ICategoriesRepository
import com.aliumujib.artic.domain.repositories.settings.ISettingsRepository
import com.aliumujib.artic.remote.api.WordPressAPI
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(modules = [
    ContextModule::class,
    RemoteModule::class,
    DataModule::class,
    CacheModule::class,
    UtilsModule::class
])
interface CoreComponent {

    fun context(): Context

    fun wordPressAPI(): WordPressAPI

    fun articlesRepository(): IArticlesRepository

    fun categoriesRepository(): ICategoriesRepository

    fun settingsRepository(): ISettingsRepository

    fun postExecutionThread(): PostExecutionThread


}
