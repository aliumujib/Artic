package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.test.TestPostExecutionThreadImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class UnSetArticleBookmarkStatusTest {

    private lateinit var unBookmarkArticle: UnBookmarkArticle
    @MockK(relaxed = true)
    lateinit var articlesRepository: IArticlesRepository
    private val postExecutionThread: PostExecutionThread = TestPostExecutionThreadImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        unBookmarkArticle = UnBookmarkArticle(articlesRepository, postExecutionThread)
    }


    @Test
    fun `test that calling bookmarkArticle completes`() = runBlockingTest {
        stubUnBookmarkOperation()
        val result = unBookmarkArticle.invoke(UnBookmarkArticle.Params(randomBuild()))
        assertThat(result, `is`((instanceOf(Unit::class.java))))
    }


    @Test(expected = IllegalStateException::class)
    fun `test that calling bookmarkArticle without params throws an exception`() = runBlockingTest{
        stubUnBookmarkOperation()
        val result = unBookmarkArticle.invoke()
        assertThat(result, `is`(instanceOf(IllegalStateException::class.java)))
    }


    private fun stubUnBookmarkOperation() {
        coEvery {
            articlesRepository.unBookmarkArticle(any())
        } returns Unit
    }

}