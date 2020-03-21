package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.test.ArticleDataFactory
import com.aliumujib.artic.domain.test.TestPostExecutionThreadImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
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
        MatcherAssert.assertThat(result, `is`((instanceOf(Article::class.java))))
    }


    @Test
    fun `test that calling bookmarkArticle with true completes`() = runBlockingTest {
        val article = ArticleDataFactory.makeProject()
        stubUnBookmarkOperation(article)
        val result = setArticleBookmarkStatus.invoke(SetArticleBookmarkStatus.Params(article, true))
        MatcherAssert.assertThat(result, `is`((instanceOf(Article::class.java))))
    }


    @Test(expected = IllegalStateException::class)
    fun `test that calling bookmarkArticle without params throws an exception`() = runBlockingTest {
        stubBookmarkOperation(randomBuild())
        val result = setArticleBookmarkStatus.invoke()
        MatcherAssert.assertThat(result, `is`((instanceOf(IllegalStateException::class.java))))
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