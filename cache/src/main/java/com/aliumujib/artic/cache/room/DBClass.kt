package com.eyowo.android.cache.room


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aliumujib.artic.cache.room.ArticlesDao


@Database(
    entities = [ArticlesDao::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class DBClass : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

}