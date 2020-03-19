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


class SearchAllArticlesTest {

    private lateinit var searchArticles: SearchAllArticles
    @MockK(relaxed = true)
    lateinit var articlesRepository: IArticlesRepository
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        searchArticles = SearchAllArticles(articlesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling searchArticles returns data`()= runBlockingTest {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubSearchArticles(flow {
            emit(list)
        })
        val result =
            searchArticles.build(SearchAllArticles.Params.make(ArticleDataFactory.makeProject().title, randomBuild())).first()
        assertThat(result, `is`((equalTo(list))))
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using searchArticles with params throws an exception`()= runBlockingTest {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubSearchArticles(flow {
            emit(list)
        })
        val result = searchArticles.build().first()
        assertThat(result, `is`((instanceOf(IllegalStateException::class.java))))
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using searchArticles with an emptyString throws an exception`() = runBlockingTest{
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubSearchArticles(flow {
            emit(projects)
        })
        val result = searchArticles.build(SearchAllArticles.Params.make("", 0)).first()
        assertThat(result, `is`((instanceOf(IllegalStateException::class.java))))
    }

    private fun stubSearchArticles(flow: Flow<List<Article>>) {
        every {
            articlesRepository.searchArticles(any(), any())
        } returns flow
    }

}