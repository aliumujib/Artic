package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.threadexecutor.PostExecutionThread
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
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
    fun `test that calling bookmarkArticle completes`() = runBlockingTest {
            stubBookmarkOperation()
            val result = setArticleBookmarkStatus.invoke(SetArticleBookmarkStatus.Params(randomBuild()))
            MatcherAssert.assertThat(result, `is`((instanceOf(Unit::class.java))))
    }


    @Test(expected = IllegalStateException::class)
    fun `test that calling bookmarkArticle without params throws an exception`() = runBlockingTest {
        stubBookmarkOperation()
        val result = setArticleBookmarkStatus.invoke()
        MatcherAssert.assertThat(result, `is`((instanceOf(IllegalStateException::class.java))))
    }


    private fun stubBookmarkOperation() {
        coEvery {
            articlesRepository.bookmarkArticle(any())
        } returns Unit
    }

}