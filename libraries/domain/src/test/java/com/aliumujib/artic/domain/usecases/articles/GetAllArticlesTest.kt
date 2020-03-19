package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.test.ArticleDataFactory
import com.aliumujib.artic.domain.test.TestPostExecutionThreadImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
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
    fun `confirm that calling getArticles returns data`() = runBlockingTest{
        val list = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(flow {
            emit(list)
        })
        val result = getArticles.build(GetAllArticles.Params.make(randomBuild(), randomBuild())).first()
        assertThat(result, `is`((equalTo(list))))
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using getArticles with params throws an exception`() = runBlockingTest {
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(flow {
            emit(projects)
        })
        val result = getArticles.build().first()
        assertThat(result, `is`((instanceOf(IllegalStateException::class.java))))
    }

    private fun stubGetArticles(flow: Flow<List<Article>>) {
        every {
            articlesRepository.getArticles(any(), any())
        } returns flow
    }

}