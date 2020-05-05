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
package com.aliumujib.artic.di.modules

import android.content.Context
import androidx.room.Room
import com.aliumujib.artic.cache.impl.ArticlesCacheImpl
import com.aliumujib.artic.cache.impl.SettingsCacheImpl
import com.aliumujib.artic.cache.room.ArticlesDao
import com.aliumujib.artic.data.repositories.articles.cache.IArticlesCache
import com.aliumujib.artic.cache.room.DBClass
import com.aliumujib.artic.data.repositories.settings.cache.ISettingsCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CacheModule {


    @Singleton
    @Provides
    fun providesArticlesCache(articlesCache: ArticlesCacheImpl): IArticlesCache {
        return articlesCache
    }

    @Singleton
    @Provides
    fun providesSettingsCache(settingsCache: SettingsCacheImpl): ISettingsCache {
        return settingsCache
    }


    @Singleton
    @Provides
    fun providesArticlesDao(dBClass: DBClass): ArticlesDao {
        return dBClass.articlesDao()
    }

    @Singleton
    @Provides
    fun providesDB(context: Context): DBClass {
        return Room.databaseBuilder(
            context.applicationContext,
            DBClass::class.java, "artic_database"
        ).fallbackToDestructiveMigration().build()
    }

}
