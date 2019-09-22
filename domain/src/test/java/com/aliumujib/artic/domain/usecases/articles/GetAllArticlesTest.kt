package com.aliumujib.artic.domain.usecases.articles

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
class GetAllArticlesTest {

    private lateinit var getArticles: GetAllArticles
    @Mock
    lateinit var articlesRepository: IArticlesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getArticles = GetAllArticles(articlesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling getProjects completes`() {
        stubGetArticles(Observable.just(ArticleDataFactory.makeArticlesList(2)))
        val testObserver = getArticles.buildUseCaseObservable(GetAllArticles.Params.make(
            any(),
            any())).test()
        testObserver.assertComplete()
    }

    @Test
    fun `confirm that calling getProjects returns data`() {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(Observable.just(list))
        val testObserver = getArticles.buildUseCaseObservable(GetAllArticles.Params.make(any(), any())).test()
        testObserver.assertValue(list)
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using getProjects with params throws an exception`() {
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(Observable.just(projects))
        val testObserver = getArticles.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetArticles(observable: Observable<List<Article>>) {
        whenever(articlesRepository.getArticles(any(), any()))
            .thenReturn(observable)
    }

}