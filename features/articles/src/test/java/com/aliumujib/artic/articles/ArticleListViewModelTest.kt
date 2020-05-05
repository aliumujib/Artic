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
package com.aliumujib.artic.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aliumujib.artic.androidtestutilities.livedata.getOrAwaitValue
import com.aliumujib.artic.articles.presentation.ArticleListActionProcessor
import com.aliumujib.artic.articles.presentation.ArticleListIntent
import com.aliumujib.artic.articles.presentation.ArticleListViewModel
import com.aliumujib.artic.domain.usecases.articles.GetAllArticles
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.domain.usecases.settings.FetchViewModeSettings
import com.aliumujib.artic.domain.usecases.settings.UpdateViewModeSettings
import com.aliumujib.artic.testutilities.coroutines.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleListViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var setArticleBookmarkStatus: SetArticleBookmarkStatus
    @MockK
    lateinit var getAllArticles: GetAllArticles
    @MockK
    lateinit var updateViewModeSettings: UpdateViewModeSettings
    @MockK
    lateinit var fetchViewModeSettings: FetchViewModeSettings

    private lateinit var articleListViewModel: ArticleListViewModel
    private lateinit var articleListActionProcessor: ArticleListActionProcessor


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        articleListActionProcessor = ArticleListActionProcessor(getAllArticles, setArticleBookmarkStatus, updateViewModeSettings, fetchViewModeSettings)
        articleListViewModel = ArticleListViewModel(articleListActionProcessor)
        articleListViewModel.processActions()
    }


    @Test
    fun `assert that the correct Loading ViewState is emitted when a LoadArticleListIntent is processed`() = runBlocking {
        every { this@ArticleListViewModelTest.getAllArticles.build(any()) } returns emptyFlow()
        articleListViewModel.processIntent(flowOf(ArticleListIntent.LoadArticleListIntent))
        val state = articleListViewModel.states().getOrAwaitValue()
        assertThat(state.isLoadingInitial).isTrue()
        assertThat(state.error).isNull()
    }

    @Test
    fun `assert that a Loading ViewState is emitted when a RefreshArticleListIntent is processed`() = runBlocking {
        every { this@ArticleListViewModelTest.getAllArticles.build(any()) } returns emptyFlow()
        articleListViewModel.processIntent(flowOf(ArticleListIntent.RefreshArticleListIntent))
        val state = articleListViewModel.states().getOrAwaitValue()
        assertThat(state.isLoadingInitial).isTrue()
        assertThat(state.isLoadingMore).isFalse()
        assertThat(state.error).isNull()
    }

    @Test
    fun `assert that a Loading ViewState is emitted when a FetchMoreArticleListIntent is processed`() = runBlocking {
        every { this@ArticleListViewModelTest.getAllArticles.build(any()) } returns emptyFlow()
        articleListViewModel.processIntent(flowOf(ArticleListIntent.FetchMoreArticleListIntent))
        val state = articleListViewModel.states().getOrAwaitValue()
        assertThat(state.isLoadingInitial).isFalse()
        assertThat(state.isLoadingMore).isTrue()
        assertThat(state.error).isNull()
    }

    @Test
    fun `assert that a Error ViewState is emitted when an error occurs while Loading article data`() = runBlocking {
        every { this@ArticleListViewModelTest.getAllArticles.build(any()) } returns flow {
            throw IllegalStateException()
        }
        articleListViewModel.processIntent(flowOf(ArticleListIntent.LoadArticleListIntent))
        val state = articleListViewModel.states().getOrAwaitValue()
        assertThat(state.error).isNotNull()
        assertThat(state.error).isInstanceOf(IllegalStateException::class.java)
        assertThat(state.isLoadingInitial).isFalse()
    }

}
