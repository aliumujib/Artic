package com.aliumujib.artic.domain.usecases.articles

import com.aliumujib.artic.domain.executor.PostExecutionThread
import com.aliumujib.artic.domain.models.Article
import com.aliumujib.artic.domain.repositories.articles.IArticlesRepository
import com.aliumujib.artic.domain.test.ArticleDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import konveyor.base.randomBuild
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchAllArticlesTest {

    private lateinit var searchArticles: SearchAllArticles
    @Mock
    lateinit var articlesRepository: IArticlesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        searchArticles = SearchAllArticles(articlesRepository, postExecutionThread)
    }

    @Test
    fun `confirm that calling searchArticles completes`() {
        stubGetArticles(Observable.just(ArticleDataFactory.makeArticlesList(2)))
        val testObserver = searchArticles.buildUseCaseObservable(SearchAllArticles.Params.make(
            "tomatoes",
            randomBuild())).test()
        testObserver.assertComplete()
    }

    @Test
    fun `confirm that calling searchArticles returns data`() {
        val list = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(Observable.just(list))
        val testObserver = searchArticles.buildUseCaseObservable(SearchAllArticles.Params.make("tomatoes", randomBuild())).test()
        testObserver.assertValue(list)
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using searchArticles with params throws an exception`() {
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(Observable.just(projects))
        val testObserver = searchArticles.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    @Test(expected = IllegalStateException::class)
    fun `confirm that using searchArticles with an emptyString throws an exception`() {
        val projects = ArticleDataFactory.makeArticlesList(10)
        stubGetArticles(Observable.just(projects))
        val testObserver = searchArticles.buildUseCaseObservable(SearchAllArticles.Params.make("", 0)).test()
        testObserver.assertValue(projects)
    }

    private fun stubGetArticles(observable: Observable<List<Article>>) {
        whenever(articlesRepository.searchArticles(any(), any()))
            .thenReturn(observable)
    }

}