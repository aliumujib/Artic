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
