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