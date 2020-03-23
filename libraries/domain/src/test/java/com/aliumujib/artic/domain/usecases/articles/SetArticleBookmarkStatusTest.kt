package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.exceptions.NoParamsException
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.testutils.ArticleDataFactory
import com.aliumujib.artic.domain.testutils.TestPostExecutionThreadImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class SetArticleBookmarkStatusTest {

    private lateinit var setArticleBookmarkStatus: SetArticleBookmarkStatus
    @MockK(relaxed = true)
    lateinit var articlesRepository: IArticlesRepository

    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        setArticleBookmarkStatus = SetArticleBookmarkStatus(articlesRepository, postExecutionThread)
    }


    @Test
    fun `test that calling bookmarkArticle with false completes`() = runBlockingTest {
        val article = ArticleDataFactory.makeProject()
        stubBookmarkOperation(article)
        val result = setArticleBookmarkStatus.invoke(SetArticleBookmarkStatus.Params(article, false))
        assertThat(result).isEqualTo(article)
        coVerify(exactly = 1) {
            articlesRepository.bookmarkArticle(article)
        }
    }


    @Test
    fun `test that calling bookmarkArticle with true completes`() = runBlockingTest {
        val article = ArticleDataFactory.makeProject()
        stubUnBookmarkOperation(article)
        val result = setArticleBookmarkStatus.invoke(SetArticleBookmarkStatus.Params(article, true))
        assertThat(result).isEqualTo(article)
        coVerify(exactly = 1) {
            articlesRepository.unBookmarkArticle(article.id)
        }
    }

    @Test(expected = NoParamsException::class)
    fun `test that calling bookmarkArticle without params throws an exception`() = runBlockingTest {
        stubBookmarkOperation(randomBuild())
        val result = setArticleBookmarkStatus.invoke()
        coVerify(exactly = 0) {
            articlesRepository.unBookmarkArticle(any())
            articlesRepository.bookmarkArticle(any())
        }
    }


    private fun stubUnBookmarkOperation(article: Article) {
        coEvery {
            articlesRepository.unBookmarkArticle(article.id)
        } returns article
    }

    private fun stubBookmarkOperation(article: Article) {
        coEvery {
            articlesRepository.bookmarkArticle(article)
        } returns article
    }

}