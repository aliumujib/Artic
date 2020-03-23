package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.exceptions.NoParamsException
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
import konveyor.base.randomBuild
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllArticlesTest {

    private lateinit var getArticles: GetAllArticles
    @MockK(relaxed = true)
    lateinit var articlesRepository: IArticlesRepository
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getArticles = GetAllArticles(articlesRepository, postExecutionThread)
    }


    @Test
    fun `confirm that calling getArticles returns data`() = runBlockingTest {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(flow {
            emit(list)
        })
        val params = GetAllArticles.Params.make(randomBuild(), randomBuild())
        val result = getArticles.build(params).first()
        assertThat(result).isEqualTo(list)
        coVerify(exactly = 1) {
            articlesRepository.getArticles(any(), any())
        }
    }

    @Test(expected = NoParamsException::class)
    fun `confirm that using getArticles without params throws an exception`() = runBlockingTest {
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(flow {
            emit(projects)
        })
        val result = getArticles.build().first()
        coVerify(exactly = 0) {
            articlesRepository.getArticles(any(), any())
        }
    }

    private fun stubGetArticles(flow: Flow<List<Article>>) {
        every {
            articlesRepository.getArticles(any(), any())
        } returns flow
    }

}