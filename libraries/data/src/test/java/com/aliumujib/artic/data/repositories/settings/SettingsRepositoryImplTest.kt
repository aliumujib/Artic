package com.aliumujib.artic.data.repositories.settings

import com.aliumujib.artic.data.repositories.settings.cache.ISettingsCache
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SettingsRepositoryImplTest {

    @MockK(relaxed = true)
    lateinit var settingsCache: ISettingsCache
    private lateinit var settingsRepositoryImpl: SettingsRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        settingsRepositoryImpl = SettingsRepositoryImpl(settingsCache)
    }


    @Test
    fun `check that calling setCurrentViewMode() with calls cache only once`() = runBlocking {
        val argument: Boolean = randomBuild()
        settingsRepositoryImpl.setCurrentViewMode(argument)
        coVerify(exactly = 1) {
            settingsCache.setArticleListViewMode(argument)
        }
    }

    @Test
    fun `check that calling getCurrentViewMode() with returns an data when remote call succeeds`() = runBlocking {
        val argument: Boolean = randomBuild()
        stubSettingsCacheResponse(argument)
        val viewMode = settingsRepositoryImpl.getCurrentViewMode()
        assertThat(viewMode).isEqualTo(argument)
    }

    private fun stubSettingsCacheResponse(boolean: Boolean) {
        coEvery { settingsCache.getArticleListViewMode() } returns boolean
    }

}