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
