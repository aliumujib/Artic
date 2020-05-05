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
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aliumujib.artic.cache.DummyDataFactory
import com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper
import com.aliumujib.artic.cache.models.mappers.AuthorCacheModelMapper
import com.aliumujib.artic.cache.models.mappers.CategoryCacheModelMapper
import com.aliumujib.artic.cache.room.DBClass
import com.aliumujib.artic.data.model.ArticleEntity
import com.google.common.truth.Truth.assertThat
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ArticlesCacheImplTest {

    private lateinit var articleCache: ArticlesCacheImpl

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var cacheTimeManager: CacheTimeManager
    private lateinit var context: Context

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        DBClass::class.java
    ).allowMainThreadQueries().build()

    private val authorCacheModelMapper: AuthorCacheModelMapper = AuthorCacheModelMapper()
    private val categoryCacheModelMapper: CategoryCacheModelMapper = CategoryCacheModelMapper()
    private val articlesMapper: ArticleCacheModelMapper = ArticleCacheModelMapper(
        authorCacheModelMapper,
        categoryCacheModelMapper
    )


    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Application>()
        cacheTimeManager = spyk(CacheTimeManager(context))
        articleCache = spyk(ArticlesCacheImpl(database.articlesDao(), articlesMapper, cacheTimeManager))
    }

    @Test
    fun `check that calling saveArticles on cache correctly saves`() = runBlocking {
        val data = insertDataIntoDB()
        val mappedArticles = articlesMapper.mapToModelList(data)
        coVerify(exactly = 1) {
            database.articlesDao().insert(mappedArticles)
            cacheTimeManager.saveLastCacheTime(any())
        }
    }

    @Test
    fun `check that calling getCachedArticles on returns data when there is data in DB`() {
        runBlocking {
            val data = insertDataIntoDB()
            val results = articleCache.getCachedArticles().first()
            assertThat(results).containsAtLeastElementsIn(data)
        }
    }

    @Test
    fun `check that calling getBookmarkedArticles on returns only bookmarked articles`() {
        runBlocking {
            insertDataIntoDB()
            val results = articleCache.getBookmarkedArticles().first()
            assertThat(results).isNotEmpty()
            results.forEach {
                assertThat(it.isBookmarked).isTrue()
            }
        }
    }

    @Test
    fun `check that calling clearArticles on correctly deletes articles  DB`() {
        runBlocking {
            insertDataIntoDB()
            val results = articleCache.getCachedArticles().first()
            assertThat(results).isNotEmpty()

            articleCache.clearArticles()
            val newResults = articleCache.getCachedArticles().first()
            assertThat(newResults).isEmpty()
        }
    }

    private suspend fun insertDataIntoDB(): List<ArticleEntity> {
        val data = DummyDataFactory.makeArticlesEntitiesList(10)
        articleCache.saveArticles(data)
        return data
    }

}
