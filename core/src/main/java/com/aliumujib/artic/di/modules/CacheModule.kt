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