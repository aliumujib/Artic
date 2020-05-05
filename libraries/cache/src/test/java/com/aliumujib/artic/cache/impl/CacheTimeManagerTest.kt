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

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CacheTimeManagerTest {
    private lateinit var cacheTimeManager: CacheTimeManager
    private lateinit var context: Context

    @Before
    fun setup() {
        context = getApplicationContext<Application>()
        cacheTimeManager = CacheTimeManager(context)
    }

    @Test
    fun `saveLastCacheTime saves correctly`() = runBlocking {
        val lastCacheTime = System.currentTimeMillis()
        cacheTimeManager.saveLastCacheTime(lastCacheTime)
        assertThat(cacheTimeManager.getLastCacheTime()).isEqualTo(lastCacheTime)
    }

}
