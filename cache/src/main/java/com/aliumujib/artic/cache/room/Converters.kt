package com.eyowo.android.cache.room

import androidx.room.TypeConverter
import com.eyowo.android.cache.models.transactions.TransactionMetadataCacheModel
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
    fun fromMetaDataString(value: String?): TransactionMetadataCacheModel? {
        val listType = object : TypeToken<TransactionMetadataCacheModel?>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromTransactionMetadataCacheModel(data: TransactionMetadataCacheModel?): String {
        val gson = Gson()
        return gson.toJson(data)
    }


//    @TypeConverter
//    fun fromSettingsString(value: String?): SettingsEntity? {
//        val listType = object : TypeToken<SettingsEntity?>() {
//        }.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromSettingsEntity(data: SettingsEntity?): String {
//        val gson = Gson()
//        return gson.toJson(data)
//    }

}