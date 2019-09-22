package com.aliumujib.artic.domain.interactors.articles

import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.test.ArticleDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetAllBookmarkedArticlesTest {

    private lateinit var getAllBookmarkedArticlesTest: GetAllBookmarkedArticles
    @Mock
    lateinit var articlesRepository: IArticlesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getAllBookmarkedArticlesTest = GetAllBookmarkedArticles(articlesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling getBookmarkedArticles completes`() {
        stubGetBookmarkedArticles(Observable.just(ArticleDataFactory.makeArticlesList(2)))
        val testObserver = getAllBookmarkedArticlesTest.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }


    @Test
    fun `confirm that calling getBookmarkedArticles returns data`() {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubGetBookmarkedArticles(Observable.just(list))
        val testObserver = getAllBookmarkedArticlesTest.buildUseCaseObservable().test()
        testObserver.assertValue(list)
    }


    private fun stubGetBookmarkedArticles(observable: Observable<List<Article>>) {
        whenever(articlesRepository.getBookmarkedArticles())
            .thenReturn(observable)
    }

}