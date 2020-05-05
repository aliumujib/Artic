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
