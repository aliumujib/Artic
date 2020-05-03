package com.aliumujib.artic.articledetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aliumujib.artic.androidtestutilities.livedata.getOrAwaitValue
import com.aliumujib.artic.articledetails.presentation.ArticleDetailActionProcessor
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsIntent
import com.aliumujib.artic.articledetails.presentation.ArticleDetailsViewModel
import com.aliumujib.artic.articles.test.DummyDataFactory
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.usecases.articles.GetArticleDetails
import com.aliumujib.artic.domain.usecases.articles.SetArticleBookmarkStatus
import com.aliumujib.artic.testutilities.coroutines.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.IllegalStateException

@RunWith(JUnit4::class)
class ArticleDetailsViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var setArticleBookmarkStatus: SetArticleBookmarkStatus
    @MockK
    lateinit var getArticleDetails: GetArticleDetails


    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel
    private lateinit var articleDetailActionProcessor: ArticleDetailActionProcessor


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
        articleDetailActionProcessor = ArticleDetailActionProcessor(setArticleBookmarkStatus, getArticleDetails)
        articleDetailsViewModel = ArticleDetailsViewModel(articleDetailActionProcessor)
        articleDetailsViewModel.processActions()
    }


    @Test
    fun `assert that the correct Loading ViewState is emitted when a LoadArticleDetailsIntent is processed`() = runBlocking {
        every { getArticleDetails.build(any()) } returns emptyFlow()
        articleDetailsViewModel.processIntent(flowOf(ArticleDetailsIntent.LoadArticleDetailsIntent(randomBuild())))
        val state = articleDetailsViewModel.states().getOrAwaitValue()
        assertThat(state.isLoadingComments).isTrue()
        assertThat(state.isLoadingArticleData).isFalse()
        assertThat(state.error).isNull()
    }

    @Test
    fun `assert that a Loading ViewState is emitted when a RefreshArticleDetailsIntent is processed`() = runBlocking {
        every { getArticleDetails.build(any()) } returns emptyFlow()
        articleDetailsViewModel.processIntent(flowOf(ArticleDetailsIntent.RefreshArticleDetailsIntent(randomBuild())))
        val state = articleDetailsViewModel.states().getOrAwaitValue()
        assertThat(state.isLoadingArticleData).isTrue()
        assertThat(state.isLoadingComments).isTrue()
        assertThat(state.error).isNull()
    }

    @Test
    fun `assert that a Error ViewState is emitted when an error occurs while Refreshing article data`() = runBlocking {
        every { getArticleDetails.build(any()) } returns flow {
            throw IllegalStateException()
        }
        articleDetailsViewModel.processIntent(flowOf(ArticleDetailsIntent.RefreshArticleDetailsIntent(randomBuild())))
        val state = articleDetailsViewModel.states().getOrAwaitValue()
        assertThat(state.error).isNotNull()
        assertThat(state.error).isInstanceOf(IllegalStateException::class.java)
        assertThat(state.isLoadingComments).isFalse()
        assertThat(state.isLoadingArticleData).isFalse()
    }

    @Test //THE MORE I LOOK AT THIS TEST, the more I feel it is useless. TODO, get a second opinion
    fun `assert that a Bookmarked article is emitted to the UI when SetArticleBookmarkStatusIntent is processed`() = runBlocking {
        val randomArticle: Article = DummyDataFactory.makeRandomArticle(false)
        coEvery { setArticleBookmarkStatus(any()) } returns randomArticle
        articleDetailsViewModel.processIntent(flowOf(ArticleDetailsIntent.SetArticleBookmarkStatusIntent(randomBuild(), false)))
        val state = articleDetailsViewModel.states().getOrAwaitValue()
        assertThat(state.data).isNotNull()
        assertThat(state.isLoadingComments).isFalse()
        assertThat(state.isLoadingArticleData).isFalse()
    }

}