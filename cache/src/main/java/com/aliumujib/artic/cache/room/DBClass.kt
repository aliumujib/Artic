package com.aliumujib.artic.cache.room


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aliumujib.artic.cache.models.ArticleCacheModel


@Database(
    entities = [ArticleCacheModel::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class DBClass : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

}