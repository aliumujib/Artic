package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.testutils.ArticleDataFactory
import com.aliumujib.artic.domain.testutils.TestPostExecutionThreadImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


class GetAllBookmarkedArticlesTest {

    private lateinit var getAllBookmarkedArticles: GetAllBookmarkedArticles
    @MockK(relaxed = true)
    lateinit var articlesRepository: IArticlesRepository
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getAllBookmarkedArticles = GetAllBookmarkedArticles(articlesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling getBookmarkedArticles returns data`() = runBlockingTest{
        val list = ArticleDataFactory.makeArticlesList(10)
        stubGetBookmarkedArticles(flow {
            emit(list)
        })
        val result = getAllBookmarkedArticles.build().single()
        assertThat(result).isEqualTo(list)
        coVerify(exactly = 1) {
            articlesRepository.getBookmarkedArticles()
        }
    }


    private fun stubGetBookmarkedArticles(flow: Flow<List<Article>>) {
        every {
            articlesRepository.getBookmarkedArticles()
        } returns flow
    }

}