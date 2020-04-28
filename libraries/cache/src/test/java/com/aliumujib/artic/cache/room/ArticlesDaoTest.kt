package com.aliumujib.artic.cache.room

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.aliumujib.artic.cache.DummyDataFactory
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O])
class ArticlesDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DBClass

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            DBClass::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun `check that calling getAllCountries() on dao returns correct data`() {
        runBlocking {
            val articles = DummyDataFactory.makeRandomArticleCacheModelList(10)
            database.articlesDao().insert(articles)

            val data = database.articlesDao().getAllCachedArticles().first()
            assertThat(articles).containsExactlyElementsIn(data)
        }
    }

    @Test
    fun `check that calling deleteArticle() on dao correctly deletes a single article`() {
        runBlocking {
            val articles = DummyDataFactory.makeRandomArticleCacheModelList(10)
            val article = articles.random()
            database.articlesDao().insert(articles)
            database.articlesDao().deleteArticle(article.id)
            val data = database.articlesDao().getAllCachedArticlesByDate().first()
            assertThat(data).doesNotContain(article)
        }
    }

    @Test
    fun `check that calling deleteAllArticles() on dao deletes everything`() {
        runBlocking {
            database.articlesDao().deleteAllArticles()
            val data = database.articlesDao().getAllCachedArticles().first()
            assertThat(data).isEmpty()
        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}