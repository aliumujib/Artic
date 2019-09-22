package com.aliumujib.artic.cache.impl

import android.content.Context
import com.aliumujib.artic.cache.utils.CoreSharedPrefManager
import javax.inject.Inject

class CacheTimeManager @Inject constructor(var context: Context) : CoreSharedPrefManager(context) {

    fun saveLastCacheTime(time: Long) {
        savePref(LAST_CACHE_TIME, time)
    }

    fun getLastCacheTime(): Long {
        return getPref(LAST_CACHE_TIME, 0L)!!
    }


    companion object {
        private const val APP_NAME = "ARTIC"
        private const val LAST_CACHE_TIME = "LAST_CACHE_TIME"
    }
}