package com.aliumujib.artic.domain.usecases.categories

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
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class GetAllArticlesForCategoryTest {

    private lateinit var getArticlesForCategory: GetArticlesForCategory
    @MockK(relaxed = true)
    lateinit var articlesRepository: IArticlesRepository
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getArticlesForCategory = GetArticlesForCategory(articlesRepository, postExecutionThread)
    }


    @Test
    fun `confirm that calling getArticlesForCategory returns data`() = runBlockingTest {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(flow {
            emit(list)
        })
        val result = getArticlesForCategory.build(GetArticlesForCategory.Params.make(randomBuild(), randomBuild())).first()
        assertThat(result).isEqualTo(list)
        coVerify(exactly = 1) {
            articlesRepository.getArticlesByCategoryId(any(), any())
        }
    }

    @Test(expected = NoParamsException::class)
    fun `confirm that using getArticlesForCategory without params throws an exception`() = runBlockingTest {
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(flow {
            emit(projects)
        })
        getArticlesForCategory.build().first()
        coVerify(exactly = 0) {
            articlesRepository.getArticlesByCategoryId(any(), any())
        }
    }

    private fun stubGetArticles(flow: Flow<List<Article>>) {
        every {
            articlesRepository.getArticlesByCategoryId(any(), any())
        } returns flow
    }

}