package com.aliumujib.artic.cache.room

import androidx.room.TypeConverter
import com.aliumujib.artic.cache.models.AuthorCacheModel
import com.aliumujib.artic.cache.models.CategoryCacheModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromListString(value: String?): List<Any>? {
        val listType = object : TypeToken<List<Any>?>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringArrayList(list: List<Any>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromCategoryCacheModelString(value: String?): List<CategoryCacheModel>? {
        val listType = object : TypeToken<List<CategoryCacheModel>?>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCategoryCacheModelList(data: List<CategoryCacheModel>?): String {
        val gson = Gson()
        return gson.toJson(data)
    }


    @TypeConverter
    fun fromAuthorCacheModelString(value: String?): AuthorCacheModel? {
        val listType = object : TypeToken<AuthorCacheModel?>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromAuthorCacheModelEntity(data: AuthorCacheModel?): String {
        val gson = Gson()
        return gson.toJson(data)
    }

}