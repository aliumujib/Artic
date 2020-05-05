/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
