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
class SettingsCacheImplTest {

    private lateinit var settingsCacheImpl: SettingsCacheImpl
    private lateinit var context: Context

    @Before
    fun setup() {
        context = getApplicationContext<Application>()
        settingsCacheImpl = SettingsCacheImpl(context)
    }

    @Test
    fun `setListViewMode saves correctly`() = runBlocking {
        val isGridMode = true
        assertThat(settingsCacheImpl.setArticleListViewMode(isGridMode)).isEqualTo(isGridMode)
    }

    @Test
    fun `getListViewMode fetches correctly`() = runBlocking {
        val isGridMode = false
        assertThat(settingsCacheImpl.setArticleListViewMode(isGridMode)).isEqualTo(settingsCacheImpl.getArticleListViewMode())
    }

}